package com.fullcycle.admin.catalago.infrastructure.video.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullcycle.admin.catalago.domain.video.VideoMediaType;

public record UploadMediaResponse(
        @JsonProperty("video_id") String videoId,
        @JsonProperty("media_type") VideoMediaType mediaType
) {
}