package com.fullcycle.admin.catalago.infrastructure.castmember.models;


import com.fullcycle.admin.catalago.domain.castmember.CastMemberType;

public record CreateCastMemberRequest(String name, CastMemberType type) {
}