package com.fullcycle.admin.catalago.infrastructure.video.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<VideoJpaEntity, String> {

}
