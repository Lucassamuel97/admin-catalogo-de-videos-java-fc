package com.fullcycle.admin.catalago.application.castmember.retrieve.get;

import com.fullcycle.admin.catalago.domain.castmember.CastMember;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberGateway;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberID;
import com.fullcycle.admin.catalago.domain.exceptions.NotFoundException;

import java.util.Objects;

public non-sealed class DefaultGetCastMemberByIdUseCase extends GetCastMemberByIdUseCase {

    private final CastMemberGateway castMemberGateway;

    public DefaultGetCastMemberByIdUseCase(final CastMemberGateway castMemberGateway) {
        this.castMemberGateway = Objects.requireNonNull(castMemberGateway);
    }

    @Override
    public CastMemberOutput execute(final String anIn) {
        final var aMemberId = CastMemberID.from(anIn);
        return this.castMemberGateway.findById(aMemberId)
                .map(CastMemberOutput::from)
                .orElseThrow(() -> NotFoundException.with(CastMember.class, aMemberId));
    }
}