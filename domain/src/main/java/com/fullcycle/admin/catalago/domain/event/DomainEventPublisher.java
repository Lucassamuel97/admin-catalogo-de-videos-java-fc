package com.fullcycle.admin.catalago.domain.event;

@FunctionalInterface
public interface DomainEventPublisher {
    void publishEvent(DomainEvent event);
}