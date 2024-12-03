package com.fullcycle.admin.catalago.infrastructure.video;

import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.domain.video.Video;
import com.fullcycle.admin.catalago.domain.video.VideoGateway;
import com.fullcycle.admin.catalago.domain.video.VideoID;
import com.fullcycle.admin.catalago.domain.video.VideoSearchQuery;
import com.fullcycle.admin.catalago.infrastructure.video.persistence.VideoJpaEntity;
import com.fullcycle.admin.catalago.infrastructure.video.persistence.VideoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
public class DefaultVideoGateway implements VideoGateway {

    private final VideoRepository videoRepository;

    public DefaultVideoGateway(
            final VideoRepository videoRepository
    ) {
        this.videoRepository = Objects.requireNonNull(videoRepository);
    }

    @Override
    @Transactional
    public Video create(final Video aVideo) {
        return save(aVideo);
    }

    @Override
    public void deleteById(final VideoID anId) {
        final var aVideoId = anId.getValue();
        if (this.videoRepository.existsById(aVideoId)) {
            this.videoRepository.deleteById(aVideoId);
        }
    }

    @Override
    public Optional<Video> findById(VideoID anId) {
        return Optional.empty();
    }

    @Override
    public Video update(Video aVideo) {
        return null;
    }

    @Override
    public Pagination<Video> findAll(VideoSearchQuery aQuery) {
        return null;
    }

    private Video save(final Video aVideo) {
        return this.videoRepository.save(VideoJpaEntity.from(aVideo))
                .toAggregate();
    }
}
