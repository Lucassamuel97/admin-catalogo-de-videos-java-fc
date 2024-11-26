package com.fullcycle.admin.catalago.application.castmember.create;

import com.fullcycle.admin.catalago.application.UseCase;

public sealed abstract class CreateCastMemberUseCase
        extends UseCase<CreateCastMemberCommand, CreateCastMemberOutput>
        permits DefaultCreateCastMemberUseCase {
}
