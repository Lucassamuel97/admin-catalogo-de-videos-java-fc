package com.fullcycle.admin.catalago.domain;

import com.fullcycle.admin.catalago.domain.event.DomainEvent;
import com.fullcycle.admin.catalago.domain.validation.ValidationHandler;

import java.util.List;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(ID id) {
        super(id);
    }

    protected AggregateRoot(final ID id, final List<DomainEvent> events) {
        super(id, events);
    }
}
