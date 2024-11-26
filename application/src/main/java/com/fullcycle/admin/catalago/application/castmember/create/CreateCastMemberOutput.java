package com.fullcycle.admin.catalago.application.castmember.create;

import com.fullcycle.admin.catalago.domain.castmember.CastMember;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberID;

public record CreateCastMemberOutput(
        String id
) {

    public static CreateCastMemberOutput from(final CastMemberID anId) {
        return new CreateCastMemberOutput(anId.getValue());
    }

    public static CreateCastMemberOutput from(final CastMember aMember) {
        return from(aMember.getId());
    }
}
