package com.fullcycle.admin.catalago.application.castmember.retrieve.get;

import com.fullcycle.admin.catalago.application.UseCase;

public sealed abstract class GetCastMemberByIdUseCase
        extends UseCase<String, CastMemberOutput>
        permits DefaultGetCastMemberByIdUseCase {
}
