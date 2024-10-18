package com.fullcycle.admin.catalago.domain;

import com.fullcycle.admin.catalago.domain.validation.ValidationHandler;

public abstract class AggregateRoot<ID extends Identifier> extends Entity<ID> {

    protected AggregateRoot(ID id) {
        super(id);
    }

}
