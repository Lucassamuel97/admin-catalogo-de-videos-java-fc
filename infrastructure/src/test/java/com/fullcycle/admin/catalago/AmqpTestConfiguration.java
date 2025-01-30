package com.fullcycle.admin.catalago;

import com.rabbitmq.client.Channel;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.rabbit.test.TestRabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração para testes com RabbitMQ, permitindo a captura de mensagens
 * e a utilização de mocks para conexões e canais.
 */

/**
 * Creates proxy around each class annotated with @{@link org.springframework.amqp.rabbit.annotation.RabbitListener}
 * that can be used to verify incoming messages via {@link org.springframework.amqp.rabbit.test.RabbitListenerTestHarness}.
 */
@Configuration
@RabbitListenerTest(spy = false, capture = true) // Habilita suporte a testes de RabbitListener
public class AmqpTestConfiguration {

    /**
     * Cria um TestRabbitTemplate para testes com RabbitMQ.
     */
    @Bean
    TestRabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        return new TestRabbitTemplate(connectionFactory);
    }

    /**
     * Mocka a ConnectionFactory para evitar dependência de um servidor RabbitMQ real.
     */
    @Bean
    ConnectionFactory connectionFactory() {
        final var factory = Mockito.mock(ConnectionFactory.class);
        final var connection = Mockito.mock(Connection.class);
        final var channel = Mockito.mock(Channel.class);

        BDDMockito.willReturn(connection).given(factory).createConnection();
        BDDMockito.willReturn(channel).given(connection).createChannel(Mockito.anyBoolean());
        BDDMockito.given(channel.isOpen()).willReturn(true);

        return factory;
    }

    /**
     * Cria um RabbitListenerContainerFactory usando a conexão mockada.
     * Isso permite que listeners (@RabbitListener) sejam testados sem um RabbitMQ real.
     */
    @Bean
    SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(final ConnectionFactory connectionFactory) {
        final var factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
