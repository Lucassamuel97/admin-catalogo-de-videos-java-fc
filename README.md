<a target="_blank" href="https://hub.docker.com/repository/docker/lucassamuel/admin-catalogo-de-videos-java-fc">
    <img src="https://img.shields.io/badge/docker HUB-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"/>
</a>

# 🎬 Microserviço: Admin do Catálogo de Vídeos com Java

Este repositório contém o microserviço responsável pelo backend da administração do catálogo de vídeos.  
O projeto segue os princípios da **Clean Architecture**, **DDD**, **TDD** e as melhores práticas de desenvolvimento.

## 📌 Índice
- [📖 Sobre o Projeto](#-sobre-o-projeto)
- [🛠️ Ferramentas Necessárias](#-ferramentas-necessárias)
- [🚀 Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [⚙️ Como Executar](#-como-executar)
- [🛡️ Configuração do Keycloak](#-configuração-do-keycloak)
- [📚 Referencial Teórico](#-referencial-teórico)
- [✉️ Contato](#-contato)

## 📖 Sobre o Projeto
O objetivo deste projeto é fornecer um backend escalável e bem estruturado para a administração de um catálogo de vídeos.  
Ele permite a criação, edição e gerenciamento de vídeos, categorias e gêneros, utilizando uma abordagem **orientada a domínio** e arquitetura limpa.

## 🛠️ Ferramentas Necessárias

Antes de começar, certifique-se de ter as seguintes ferramentas instaladas em seu ambiente:

- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) - Versão necessária para rodar a aplicação.
- Uma **IDE** de sua escolha ([IntelliJ IDEA](https://www.jetbrains.com/idea/), [Eclipse](https://www.eclipse.org/downloads/), [VS Code](https://code.visualstudio.com/), etc.).
- [Docker](https://www.docker.com/) - Para rodar os containers do banco de dados e mensageria.

## 🚀 Tecnologias Utilizadas

A aplicação foi desenvolvida utilizando as seguintes tecnologias e padrões:

### 🔹 Tecnologias
- **[Java](https://www.java.com/)** - Linguagem de programação robusta e amplamente utilizada para backend.
- **[Spring Boot](https://spring.io/projects/spring-boot)** - Framework que simplifica a configuração e o desenvolvimento de aplicações Java.
- **[RabbitMQ](https://www.rabbitmq.com/)** - Mensageria baseada no protocolo AMQP para comunicação assíncrona entre serviços.
- **[Swagger](https://swagger.io/)** - Ferramenta para documentação e testes de APIs RESTful.
- **[Flyway](https://flywaydb.org/)** - Ferramenta para versionamento e migração de banco de dados.
- **[MySQL](https://www.mysql.com/)** - Banco de dados relacional utilizado pela aplicação.
- **[Docker](https://www.docker.com/)** e **[Docker Compose](https://docs.docker.com/compose/)** - Para containerização e orquestração dos serviços.
- **[Keycloak](https://www.keycloak.org/)** - Provedor de identidade e gerenciamento de autenticação baseado em OAuth2 e OpenID Connect.

### 🔹 Ferramentas e Testes
- **[Mockito](https://site.mockito.org/)** - Framework para criação de mocks e testes unitários.
- **[JUnit](https://junit.org/)** - Framework para testes automatizados em Java.
- **[JaCoCo](https://www.eclemma.org/jacoco/)** - Ferramenta para análise de cobertura de código nos testes.

### 🔹 Padrões de Arquitetura
- **[DDD (Domain-Driven Design)](https://martinfowler.com/bliki/DomainDrivenDesign.html)** - Organização do código baseada no domínio do negócio.
- **[Notification Pattern](https://martinfowler.com/articles/replaceThrowWithNotification.html)** - Substituição de exceções por notificações para melhor gerenciamento de erros e validações.
- **[Clean Architecture](https://fullcycle.com.br/o-que-e-clean-architecture/)** - Separação de responsabilidades para facilitar manutenção e escalabilidade.
- **[Test-Driven Development (TDD)](https://developer.ibm.com/articles/5-steps-of-test-driven-development/)** - Desenvolvimento baseado em testes automatizados para garantir a confiabilidade do código.


## ⚙️ Como Executar

1. Clone o repositório:
    ```bash
     git clone https://github.com/Lucassamuel97/admin-catalogo-de-videos-java-fc
2. Acesse a pasta do projeto:
    ```bash
    cd admin-catalogo-de-videos-java-fc
    ```
3. Crie o .env através do .env.example e adicione suas credenciais, tambem coloque em sandbox/.env.local
    ```bash
    GOOGLE_CLOUD_CREDENTIALS=
    GOOGLE_CLOUD_PROJECT=
    ```
4. Execute o container para subir o rabbitMQ e o Mysql e KeyCloak:
    ```bash
    cd sandbox
    ./run.sh
    ```
5. Executar as migrações com Flyway
    ```bash
    ./gradlew flywayMigrate
    ```
6. Execute a aplicação após os containers de Serviços sestarem ativos:
    ```bash
    cd sandbox/app
    docker compose --profile app up -d
    ```
   > **Obs.:** Caso necessite rebuildar a imagem de sua aplicação é necessário um comando adicional:
   >```
   >docker compose build --no-cache app
   >```
7. Parando os containers (Para encerrar os containers, basta executar o comando):
   ```
   docker compose --profile app stop
   ```
## 🛡️ Configuração do Keycloak

O **Keycloak** é utilizado para gerenciar autenticação e autorização no sistema. Siga os passos abaixo para configurá-lo corretamente:

### 1️⃣ Subir o container e acessar a interface do Keycloak
Execute o comando:
   ```bash
      cd sandbox/services/
      docker-compose up -d
   ```
Após subir o container, acesse o painel de administração do Keycloak:

[http://localhost:8443](http://localhost:8443)

Faça login com as credenciais padrão:

*   **Usuário**: `admin`
*   **Senha**: `admin`

### 2️⃣  Criar um Realm
1.  Vá para **Admin Console** > "Create Realm"
2.  Nomeie o realm como `fc3-codeflix`

### 3️⃣  Criar o Client
1.  Vá para **Clients** > "Create"
2.  **Client ID**: `fc3-admin-catalogo-de-videos`
3.  **Client authentication**: `ON`
4.  **Confidential**: `ON`

### 4️⃣  Criar Role, Grupo e Usuário
1.  **Role**: `catalogo-admin`
2.  **Grupo**: `catalogo-admin` (associado à Role)
3.  **Usuário**: `myuser` (senha: `123456`, associado ao grupo `catalogo-admin`)


## 📚 Referencial Teórico
- **[FULL CYCLE](https://fullcycle.com.br/)** - Modulo Projeto prático - Java (Back-End).
- **Repositório de referência do curso**: [FC3 Admin Catálogo de Vídeos](https://github.com/devfullcycle/FC3-admin-catalogo-de-videos-java)

## ✉️ Contato
Caso tenha dúvidas ou sugestões, entre em contato:

📧 Email: [lukassamuka88@gmail.com](mailto:lukassamuka88@gmail.com)  
🔗 LinkedIn: [Lucas Samuel Pereira Godoy](https://www.linkedin.com/in/lucas-samuel-pereira-godoy/)  
