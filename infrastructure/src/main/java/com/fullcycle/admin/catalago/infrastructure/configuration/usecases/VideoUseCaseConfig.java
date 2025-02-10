package com.fullcycle.admin.catalago.infrastructure.configuration.usecases;

import com.fullcycle.admin.catalago.application.video.create.CreateVideoUseCase;
import com.fullcycle.admin.catalago.application.video.create.DefaultCreateVideoUseCase;
import com.fullcycle.admin.catalago.application.video.delete.DefaultDeleteVideoUseCase;
import com.fullcycle.admin.catalago.application.video.delete.DeleteVideoUseCase;
import com.fullcycle.admin.catalago.application.video.media.get.DefaultGetMediaUseCase;
import com.fullcycle.admin.catalago.application.video.media.get.GetMediaUseCase;
import com.fullcycle.admin.catalago.application.video.media.update.DefaultUpdateMediaStatusUseCase;
import com.fullcycle.admin.catalago.application.video.media.update.UpdateMediaStatusUseCase;
import com.fullcycle.admin.catalago.application.video.media.upload.DefaultUploadMediaUseCase;
import com.fullcycle.admin.catalago.application.video.media.upload.UploadMediaUseCase;
import com.fullcycle.admin.catalago.application.video.retrieve.get.DefaultGetVideoByIdUseCase;
import com.fullcycle.admin.catalago.application.video.retrieve.get.GetVideoByIdUseCase;
import com.fullcycle.admin.catalago.application.video.retrieve.list.DefaultListVideosUseCase;
import com.fullcycle.admin.catalago.application.video.retrieve.list.ListVideosUseCase;
import com.fullcycle.admin.catalago.application.video.update.DefaultUpdateVideoUseCase;
import com.fullcycle.admin.catalago.application.video.update.UpdateVideoUseCase;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberGateway;
import com.fullcycle.admin.catalago.domain.category.CategoryGateway;
import com.fullcycle.admin.catalago.domain.genre.GenreGateway;
import com.fullcycle.admin.catalago.domain.video.MediaResourceGateway;
import com.fullcycle.admin.catalago.domain.video.VideoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Objects;
@Configuration
public class VideoUseCaseConfig {

    private final CategoryGateway categoryGateway;
    private final CastMemberGateway castMemberGateway;
    private final GenreGateway genreGateway;
    private final MediaResourceGateway mediaResourceGateway;
    private final VideoGateway videoGateway;

    public VideoUseCaseConfig(
            final CategoryGateway categoryGateway,
            final CastMemberGateway castMemberGateway,
            final GenreGateway genreGateway,
            final MediaResourceGateway mediaResourceGateway,
            final VideoGateway videoGateway
    ) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
        this.genreGateway = Objects.requireNonNull(genreGateway);
        this.mediaResourceGateway = Objects.requireNonNull(mediaResourceGateway);
        this.videoGateway = Objects.requireNonNull(videoGateway);
    }

    @Bean
    public CreateVideoUseCase createVideoUseCase() {
        return new DefaultCreateVideoUseCase(categoryGateway, castMemberGateway, genreGateway, mediaResourceGateway, videoGateway);
    }

    @Bean
    public UpdateVideoUseCase updateVideoUseCase() {
        return new DefaultUpdateVideoUseCase(videoGateway, categoryGateway, castMemberGateway, genreGateway, mediaResourceGateway);
    }

    @Bean
    public GetVideoByIdUseCase getVideoByIdUseCase() {
        return new DefaultGetVideoByIdUseCase(videoGateway);
    }

    @Bean
    public DeleteVideoUseCase deleteVideoUseCase() {
        return new DefaultDeleteVideoUseCase(videoGateway, mediaResourceGateway);
    }

    @Bean
    public ListVideosUseCase listVideosUseCase() {
        return new DefaultListVideosUseCase(videoGateway);
    }

    @Bean
    public GetMediaUseCase getMediaUseCase() {
        return new DefaultGetMediaUseCase(mediaResourceGateway);
    }

    @Bean
    public UploadMediaUseCase uploadMediaUseCase() {
        return new DefaultUploadMediaUseCase(mediaResourceGateway, videoGateway);
    }

    @Bean
    public UpdateMediaStatusUseCase updateMediaStatusUseCase() {
        return new DefaultUpdateMediaStatusUseCase(videoGateway);
    }
}