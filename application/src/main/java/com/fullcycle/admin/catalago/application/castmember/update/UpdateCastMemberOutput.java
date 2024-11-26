package com.fullcycle.admin.catalago.application.castmember.update;

import com.fullcycle.admin.catalago.domain.castmember.CastMember;
import com.fullcycle.admin.catalago.domain.castmember.CastMemberID;

public record UpdateCastMemberOutput(String id) {

    public static UpdateCastMemberOutput from(final CastMemberID anId) {
        return new UpdateCastMemberOutput(anId.getValue());
    }

    public static UpdateCastMemberOutput from(final CastMember aMember) {
        return from(aMember.getId());
    }
}
