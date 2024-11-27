package com.fullcycle.admin.catalago.infrastructure.castmember.presenter;

import com.fullcycle.admin.catalago.application.castmember.retrieve.get.CastMemberOutput;
import com.fullcycle.admin.catalago.infrastructure.castmember.models.CastMemberResponse;

public interface CastMemberPresenter {

    static CastMemberResponse present(final CastMemberOutput aMember) {
        return new CastMemberResponse(
                aMember.id(),
                aMember.name(),
                aMember.type().name(),
                aMember.createdAt().toString(),
                aMember.updatedAt().toString()
        );
    }
}