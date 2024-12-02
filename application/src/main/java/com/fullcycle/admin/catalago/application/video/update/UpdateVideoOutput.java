package com.fullcycle.admin.catalago.application.video.update;

import com.fullcycle.admin.catalago.domain.video.Video;

public record UpdateVideoOutput(String id) {

    public static UpdateVideoOutput from(final Video aVideo) {
        return new UpdateVideoOutput(aVideo.getId().getValue());
    }
}
