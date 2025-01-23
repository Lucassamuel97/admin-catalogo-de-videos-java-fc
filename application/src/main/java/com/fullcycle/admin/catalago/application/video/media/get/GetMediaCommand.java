package com.fullcycle.admin.catalago.application.video.media.get;

public record GetMediaCommand(
        String videoId,
        String mediaType
) {

    public static GetMediaCommand with(final String anId, final String aType) {
        return new GetMediaCommand(anId, aType);
    }
}