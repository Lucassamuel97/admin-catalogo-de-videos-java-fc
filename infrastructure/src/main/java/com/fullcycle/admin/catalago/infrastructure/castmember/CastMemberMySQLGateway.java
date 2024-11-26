package com.fullcycle.admin.catalago.infrastructure.castmember;


import com.fullcycle.admin.catalago.domain.castmember.CastMember;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberGateway;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberID;
import com.fullcycle.admin.catalago.domain.pagination.Pagination;
import com.fullcycle.admin.catalago.domain.pagination.SearchQuery;
import com.fullcycle.admin.catalago.infrastructure.castmember.persistence.CastMemberJpaEntity;
import com.fullcycle.admin.catalago.infrastructure.castmember.persistence.CastMemberRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CastMemberMySQLGateway implements CastMemberGateway {

    private final CastMemberRepository castMemberRepository;

    public CastMemberMySQLGateway(final CastMemberRepository castMemberRepository) {
        this.castMemberRepository = Objects.requireNonNull(castMemberRepository);
    }

    @Override
    public CastMember create(final CastMember aCastMember) {
        return save(aCastMember);
    }

    @Override
    public void deleteById(final CastMemberID anId) {

    }

    @Override
    public Optional<CastMember> findById(final CastMemberID anId) {
        return Optional.empty();
    }

    @Override
    public CastMember update(final CastMember aCastMember) {
        return null;
    }

    @Override
    public Pagination<CastMember> findAll(final SearchQuery aQuery) {
        return null;
    }

    @Override
    public List<CastMemberID> existsByIds(final Iterable<CastMemberID> ids) {
        return List.of();
    }

    private CastMember save(final CastMember aCastMember) {
        return this.castMemberRepository.save(CastMemberJpaEntity.from(aCastMember))
                .toAggregate();
    }
}
