package com.fullcycle.admin.catalago.application.castmember.update;

import com.fullcycle.admin.catalago.application.UseCase;

public sealed abstract class UpdateCastMemberUseCase
        extends UseCase<UpdateCastMemberCommand, UpdateCastMemberOutput>
        permits DefaultUpdateCastMemberUseCase {
}
