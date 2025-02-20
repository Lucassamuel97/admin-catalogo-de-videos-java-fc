<a target="_blank" href="https://hub.docker.com/repository/docker/lucassamuel/admin-catalogo-de-videos-java-fc"><img src="https://img.shields.io/badge/docker HUB-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"/></a>

#  Microserviço: Admin do Catálogo de Vídeos com Java

Este repositório contem o Microserviço referente ao backend da Administração do Catálogo de Vídeos
Utilizando Clean Architecture, DDD, TDD e as boas práticas atuais de mercado

## Tecnologias Utilizadas

A aplicação foi desenvolvida utilizando as seguintes tecnologias:

- [Java](https://www.java.com/): Linguagem de programação robusta e amplamente utilizada para desenvolvimento backend.
- [Spring Boot](https://spring.io/projects/spring-boot): Framework para aplicações Java que simplifica a configuração e o desenvolvimento.
- [RabbitMQ](https://www.rabbitmq.com/): Mensageria baseada no protocolo AMQP para comunicação assíncrona entre serviços.
- [Swagger](https://swagger.io/): Ferramenta para documentação e testes de APIs RESTful.
- [Mockito](https://site.mockito.org/): Framework para criação de mocks e testes unitários em Java.
- [JUnit](https://junit.org/): Framework de testes para desenvolvimento orientado a testes (TDD) em Java.
- [JaCoCo](https://www.eclemma.org/jacoco/): Ferramenta para análise de cobertura de código em testes Java.
- [Flyway](https://flywaydb.org/): Ferramenta para versionamento e migração de banco de dados.
- [Vavr](https://www.vavr.io/): Biblioteca funcional para Java que melhora a imutabilidade e reduz boilerplate.
- [Dockerfile](https://docs.docker.com/engine/reference/builder/): Definição de imagens Docker para facilitar a criação e execução de ambientes isolados.
- [Docker Compose](https://docs.docker.com/compose/): Ferramenta para definir e gerenciar múltiplos containers Docker de forma coordenada.

- [DDD (Domain-Driven Design)](https://martinfowler.com/bliki/DomainDrivenDesign.html): Abordagem de design que enfatiza o domínio do negócio como base para a modelagem da aplicação.
- [Notification Pattern](https://martinfowler.com/articles/replaceThrowWithNotification.html): Padrão que substitui exceções por notificações para melhor gerenciamento de erros e validações.
- [Clean Architecture](https://fullcycle.com.br/o-que-e-clean-architecture/): Padrão de arquitetura que promove separação de responsabilidades e facilita manutenção.

Essas tecnologias foram escolhidas para garantir escalabilidade, modularidade e boas práticas no desenvolvimento da aplicação.

## Executar a aplicação utilizando Docker

1. Clone o repositório:
    ```bash
     git clone https://github.com/Lucassamuel97/admin-catalogo-de-videos-java-fc
2. Acesse a pasta do projeto:
    ```bash
    cd admin-catalogo-de-videos-java-fc
    ```
3. Crie o .env atraves do .env.example e adicione suas credenciais:
    ```bash
    GOOGLE_CLOUD_CREDENTIALS=
    GOOGLE_CLOUD_PROJECT=
    ```
4. Execute o container para subir o rabbitMQ e o Mysql:
    ```bash
    docker-compose up -d
    ```
5. Execute a aplicação após o container mysql e rabbitMQ estarem ativos:
    ```bash
    docker compose --profile app up -d
    ```
    
    
