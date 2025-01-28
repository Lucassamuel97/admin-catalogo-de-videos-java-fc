package com.fullcycle.admin.catalago.domain.video;

import com.fullcycle.admin.catalago.domain.event.DomainEvent;
import com.fullcycle.admin.catalago.domain.utils.InstantUtils;

import java.time.Instant;

public record VideoMediaCreated(
        String resourceId,
        String filePath,
        Instant occurredOn
) implements DomainEvent {

    public VideoMediaCreated(final String resourceId, final String filePath) {
        this(resourceId, filePath, InstantUtils.now());
    }
}
