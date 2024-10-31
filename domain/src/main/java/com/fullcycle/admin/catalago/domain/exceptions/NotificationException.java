package com.fullcycle.admin.catalago.domain.exceptions;


import com.fullcycle.admin.catalago.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String aMessage, final Notification notification) {
        super(aMessage, notification.getErrors());
    }
}

