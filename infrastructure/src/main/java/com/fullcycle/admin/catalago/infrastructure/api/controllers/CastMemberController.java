package com.fullcycle.admin.catalago.infrastructure.api.controllers;

import com.fullcycle.admin.catalago.infrastructure.api.CastMemberAPI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CastMemberController implements CastMemberAPI {

    @Override
    public ResponseEntity<?> create(final Object input) {
        return null;
    }
}
