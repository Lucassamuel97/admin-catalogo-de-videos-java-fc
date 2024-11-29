package com.fullcycle.admin.catalago.domain.video;

import java.util.Optional;

public interface MediaResourceGateway {

    AudioVideoMedia storeAudioVideo(VideoID anId, Resource aResource);

    ImageMedia storeImage(VideoID anId, Resource aResource);

    Optional<Resource> getResource(VideoID anId, VideoMediaType type);

    void clearResources(VideoID anId);
}
