package com.fullcycle.admin.catalago.infrastructure.configuration;

import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Define esta classe como uma configuração do Spring
@Configuration
// Habilita a segurança da web no Spring Security
@EnableWebSecurity
// Habilita a segurança a nível de método (@Secured e @RolesAllowed)
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    // Definição de constantes para representar os papéis (roles) do sistema
    private static final String ROLE_ADMIN = "CATALOGO_ADMIN";
    private static final String ROLE_CAST_MEMBERS = "CATALOGO_CAST_MEMBERS";
    private static final String ROLE_CATEGORIES = "CATALOGO_CATEGORIES";
    private static final String ROLE_GENRES = "CATALOGO_GENRES";
    private static final String ROLE_VIDEOS = "CATALOGO_VIDEOS";

    // Método que define a configuração da segurança da aplicação
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                // Desativa a proteção contra CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())

                // Define as regras de autorização para os endpoints
                .authorizeHttpRequests(authorize -> {
                    authorize
                            // Apenas usuários com ROLE_ADMIN ou ROLE_CAST_MEMBERS podem acessar /cast_members*
                            .antMatchers("/cast_members*").hasAnyRole(ROLE_ADMIN, ROLE_CAST_MEMBERS)

                            // Apenas usuários com ROLE_ADMIN ou ROLE_CATEGORIES podem acessar /categories*
                            .antMatchers("/categories*").hasAnyRole(ROLE_ADMIN, ROLE_CATEGORIES)

                            // Apenas usuários com ROLE_ADMIN ou ROLE_GENRES podem acessar /genres*
                            .antMatchers("/genres*").hasAnyRole(ROLE_ADMIN, ROLE_GENRES)

                            // Apenas usuários com ROLE_ADMIN ou ROLE_VIDEOS podem acessar /videos*
                            .antMatchers("/videos*").hasAnyRole(ROLE_ADMIN, ROLE_VIDEOS)

                            // Qualquer outra requisição precisa do papel ROLE_ADMIN
                            .anyRequest().hasRole(ROLE_ADMIN);
                })

                // Configura a autenticação via JWT, usando um conversor personalizado (KeycloakJwtConverter)
                .oauth2ResourceServer(oauth -> {
                    oauth.jwt()
                            .jwtAuthenticationConverter(new KeycloakJwtConverter());
                })

                // Define a política de sessão como STATELESS (não mantém sessão no servidor)
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })

                // Permite o uso de iframes apenas da mesma origem
                .headers(headers -> {
                    headers.frameOptions().sameOrigin();
                })

                .build();
    }

    // Classe que converte um token JWT em um objeto de autenticação do Spring Security
    static class KeycloakJwtConverter implements Converter<Jwt, AbstractAuthenticationToken> {

        private final KeycloakAuthoritiesConverter authoritiesConverter;

        public KeycloakJwtConverter() {
            this.authoritiesConverter = new KeycloakAuthoritiesConverter();
        }

        @Override
        public AbstractAuthenticationToken convert(final Jwt jwt) {
            // Cria um objeto de autenticação baseado no token JWT recebido
            return new JwtAuthenticationToken(jwt, extractAuthorities(jwt), extractPrincipal(jwt));
        }

        // Extrai o identificador do usuário do token JWT
        private String extractPrincipal(final Jwt jwt) {
            return jwt.getClaimAsString(JwtClaimNames.SUB);
        }

        // Extrai as permissões (authorities) do usuário a partir do token JWT
        private Collection<? extends GrantedAuthority> extractAuthorities(final Jwt jwt) {
            return this.authoritiesConverter.convert(jwt);
        }
    }

    // Classe que converte as roles (papéis) do token JWT em autoridades do Spring Security
    static class KeycloakAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

        private static final String REALM_ACCESS = "realm_access";
        private static final String ROLES = "roles";
        private static final String RESOURCE_ACCESS = "resource_access";
        private static final String SEPARATOR = "_";
        private static final String ROLE_PREFIX = "ROLE_";

        @Override
        public Collection<GrantedAuthority> convert(final Jwt jwt) {
            // Extrai as roles do realm e do resource access
            final var realmRoles = extractRealmRoles(jwt);
            final var resourceRoles = extractResourceRoles(jwt);

            // Concatena os roles extraídos e adiciona o prefixo "ROLE_"
            return Stream.concat(realmRoles, resourceRoles)
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role.toUpperCase()))
                    .collect(Collectors.toSet());
        }

        // Extrai os papéis do "resource_access" do token JWT
        private Stream<String> extractResourceRoles(final Jwt jwt) {

            final Function<Map.Entry<String, Object>, Stream<String>> mapResource =
                    resource -> {
                        final var key = resource.getKey();
                        final var value = (JSONObject) resource.getValue();
                        final var roles = (Collection<String>) value.get(ROLES);

                        // Concatena o nome do recurso com a role (ex: "catalogo_admin")
                        return roles.stream().map(role -> key.concat(SEPARATOR).concat(role));
                    };

            final Function<Set<Map.Entry<String, Object>>, Collection<String>> mapResources =
                    resources -> resources.stream()
                            .flatMap(mapResource)
                            .toList();

            // Obtém as permissões do usuário no "resource_access" e as retorna como uma lista
            return Optional.ofNullable(jwt.getClaimAsMap(RESOURCE_ACCESS))
                    .map(resources -> resources.entrySet())
                    .map(mapResources)
                    .orElse(Collections.emptyList())
                    .stream();
        }

        // Extrai os papéis do "realm_access" do token JWT
        private Stream<String> extractRealmRoles(final Jwt jwt) {
            return Optional.ofNullable(jwt.getClaimAsMap(REALM_ACCESS))
                    .map(resource -> (Collection<String>) resource.get(ROLES))
                    .orElse(Collections.emptyList())
                    .stream();
        }
    }
}
