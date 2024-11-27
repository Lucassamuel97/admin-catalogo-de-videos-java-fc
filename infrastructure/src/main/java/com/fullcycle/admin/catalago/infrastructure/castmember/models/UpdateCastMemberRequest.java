package com.fullcycle.admin.catalago.infrastructure.castmember.models;

import com.fullcycle.admin.catalago.domain.castmember.CastMemberType;

public record UpdateCastMemberRequest(String name, CastMemberType type) {
}
