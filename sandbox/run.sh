#!/bin/bash

# Este script automatiza a configuração completa do ambiente de desenvolvimento Docker,
# incluindo redes, diretórios, geração de certificados SSL/TLS para o Keycloak
# e configuração do truststore da JVM na aplicação Spring Boot.

# --- 1. Criar as Docker Networks ---
echo "Criando redes Docker..."
# Cria a rede 'adm_videos_services' se ela não existir. '|| true' evita erro se já existir.
docker network create adm_videos_services || true
# Cria a rede 'elastic' se ela não existir.
docker network create elastic || true

# --- 2. Criar e Configurar Pastas ---
echo "Criando e configurando diretórios para persistência e certificados..."
# Cria diretórios para MySQL, Elasticsearch, Keycloak e Filebeat
sudo mkdir -p .docker/mysql
sudo mkdir -p .docker/es01
sudo mkdir -p .docker/keycloak
sudo mkdir -p .docker/filebeat

# Cria e garante permissões de escrita para a pasta de dados do Keycloak (para banco H2 interno, se usado)
sudo mkdir -p .docker/keycloak_data
sudo chmod -R 777 .docker/keycloak_data

# Cria o diretório para os certificados do Keycloak e garante permissões totais
sudo mkdir -p .docker/keycloak/certs
sudo chmod -R 777 .docker/keycloak/certs

# --- 3. Gerar Certificado SSL/TLS Self-Signed para Keycloak com SAN (Subject Alternative Names) ---
# Esta opção gera um certificado mais robusto, que é melhor aceito por JVMs modernas.
if [ ! -f ".docker/keycloak/certs/cert.pem" ] || [ ! -f ".docker/keycloak/certs/key.pem" ]; then
  echo "Gerando certificados SSL para Keycloak com Subject Alternative Names (SAN)..."

  # Cria um arquivo de configuração temporário para o OpenSSL com as entradas SAN
  # O Common Name (CN) é 'keycloak', que é o hostname usado dentro da rede Docker.
  # Adiciona também 'localhost' e '127.0.0.1' para acesso via Postman/navegador na máquina host.
  cat > .docker/keycloak/certs/openssl.cnf <<EOF
[req]
distinguished_name = req_distinguished_name
x509_extensions = v3_req
prompt = no
[req_distinguished_name]
CN = keycloak
[v3_req]
keyUsage = critical, digitalSignature, keyEncipherment
extendedKeyUsage = serverAuth, clientAuth
subjectAltName = @alt_names
[alt_names]
DNS.1 = keycloak
DNS.2 = localhost
IP.1 = 127.0.0.1
EOF

  # Gera o certificado X.509 (x509), nova chave RSA de 4096 bits (-newkey rsa:4096),
  # sem criptografar a chave (-nodes), validade de 365 dias (-days 365),
  # e usando o arquivo de configuração SAN (-config).
  openssl req -x509 -newkey rsa:4096 \
    -keyout .docker/keycloak/certs/key.pem \
    -out .docker/keycloak/certs/cert.pem \
    -days 365 -nodes \
    -config .docker/keycloak/certs/openssl.cnf

  # Remove o arquivo de configuração temporário
  sudo rm .docker/keycloak/certs/openssl.cnf
  # Define permissões de leitura para os certificados (essencial para o Keycloak)
  sudo chmod 644 .docker/keycloak/certs/*.pem
else
  echo "Certificados SSL para Keycloak já existem, pulando geração."
fi

# --- 4. Garantir Permissões para o realm-export.json ---
# Certifica-se de que o arquivo de exportação do realm Keycloak é legível
if [ -f "services/realm-export.json" ]; then
  echo "Definindo permissões de leitura para services/realm-export.json..."
  sudo chmod 644 services/realm-export.json
else
  echo "Aviso: services/realm-export.json não encontrado para definir permissões. Certifique-se de que o arquivo existe na pasta 'sandbox/services/'."
fi

# --- 5. Iniciar TODOS os Containers Primeiro (Keycloak E sua aplicação) ---
echo "Iniciando TODOS os containers (Serviços e Aplicação Spring Boot)..."
# Inicia os serviços definidos em services/docker-compose.yml (Keycloak, MySQL, RabbitMQ)
docker-compose -f services/docker-compose.yml up -d
# Inicia a aplicação Spring Boot. Ela precisa estar rodando para interagir com ela.
docker-compose -f app/docker-compose.yml up -d

# Dê um tempo para todos os containers estarem completamente de pé.
# Você pode precisar ajustar esse tempo dependendo do quão rápido sua app e Keycloak iniciam.
sleep 30

# --- 6. Configurar o Certificado SSL/TLS no Container da Aplicação Spring Boot ---
# Esta etapa só pode ser executada APÓS o container da aplicação estar de pé.

# 6.1. Copiar o certificado do host para dentro do container da aplicação Spring Boot
echo "Copiando certificado do Keycloak para o container da aplicação Spring Boot ('adm_videos_app')..."
docker cp ./.docker/keycloak/certs/cert.pem adm_videos_app:/tmp/keycloak.crt

# 6.2. Importar o certificado para o truststore da JVM dentro do container da aplicação
echo "Importando certificado do Keycloak para o truststore da JVM no container da aplicação..."
# **** MUDANÇA ESSENCIAL: Executa o keytool como 'root' dentro do container para permissão de escrita ****
# O caminho '/opt/java/openjdk/lib/security/cacerts' foi confirmado como o correto.
docker exec -u root adm_videos_app keytool -import -trustcacerts -file /tmp/keycloak.crt \
  -keystore /opt/java/openjdk/lib/security/cacerts -storepass changeit -alias keycloak-dev-cert -noprompt

# --- 7. Forçar o Reinício do Container da Aplicação Spring Boot ---
echo "Forçando reinício do container da aplicação Spring Boot para carregar o truststore atualizado..."
# É crucial reiniciar o container após a modificação do cacerts para que a JVM o recarregue.
docker-compose -f app/docker-compose.yml restart adm_videos_app

# --- 8. Iniciar Outros Containers (se houver, como ELK Stack) ---
# Descomente as linhas abaixo se você utiliza outros docker-compose files
# echo "Inicializando os containers ELK (se configurado)..."
# docker-compose -f elk/docker-compose.yml up -d

echo "Todos os containers foram inicializados, configurados e a aplicação foi reiniciada."
sleep 10