package com.fullcycle.admin.catalago.infrastructure.configuration;

import com.fullcycle.admin.catalago.infrastructure.configuration.annotations.VideoCreatedQueue;
import com.fullcycle.admin.catalago.infrastructure.configuration.properties.amqp.QueueProperties;
import com.fullcycle.admin.catalago.infrastructure.services.EventService;
import com.fullcycle.admin.catalago.infrastructure.services.impl.RabbitEventService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConfig {

    @Bean
    @VideoCreatedQueue
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}
