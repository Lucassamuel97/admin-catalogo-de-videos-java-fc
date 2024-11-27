package com.fullcycle.admin.catalago.infrastructure.api.controllers;

import com.fullcycle.admin.catalago.application.castmember.create.CreateCastMemberCommand;
import com.fullcycle.admin.catalago.application.castmember.create.CreateCastMemberUseCase;
import com.fullcycle.admin.catalago.infrastructure.api.CastMemberAPI;
import com.fullcycle.admin.catalago.infrastructure.castmember.models.CreateCastMemberRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class CastMemberController implements CastMemberAPI {

    private final CreateCastMemberUseCase createCastMemberUseCase;

    public CastMemberController(CreateCastMemberUseCase createCastMemberUseCase) {
        this.createCastMemberUseCase = createCastMemberUseCase;
    }

    @Override
    public ResponseEntity<?> create(final CreateCastMemberRequest input) {
        final var aCommand =
                CreateCastMemberCommand.with(input.name(), input.type());

        final var output = this.createCastMemberUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/cast_members/" + output.id())).body(output);
    }
}
