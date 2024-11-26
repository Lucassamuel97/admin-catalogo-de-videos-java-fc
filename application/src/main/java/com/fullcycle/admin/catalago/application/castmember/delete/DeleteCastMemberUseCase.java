package com.fullcycle.admin.catalago.application.castmember.delete;

import com.fullcycle.admin.catalago.application.UnitUseCase;

public sealed abstract class DeleteCastMemberUseCase
        extends UnitUseCase<String>
        permits DefaultDeleteCastMemberUseCase {
}