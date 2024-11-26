package com.fullcycle.admin.catalago.infrastructure.castmember.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface CastMemberRepository extends JpaRepository<CastMemberJpaEntity, String> {

    Page<CastMemberJpaEntity> findAll(Specification<CastMemberJpaEntity> specification, Pageable page);

}
