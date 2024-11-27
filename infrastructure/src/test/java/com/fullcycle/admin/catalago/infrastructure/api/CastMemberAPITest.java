package com.fullcycle.admin.catalago.infrastructure.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcycle.admin.catalago.ControllerTest;
import com.fullcycle.admin.catalago.application.castmember.create.DefaultCreateCastMemberUseCase;
import com.fullcycle.admin.catalago.application.castmember.delete.DefaultDeleteCastMemberUseCase;
import com.fullcycle.admin.catalago.application.castmember.retrieve.get.DefaultGetCastMemberByIdUseCase;
import com.fullcycle.admin.catalago.application.castmember.retrieve.list.DefaultListCastMembersUseCase;
import com.fullcycle.admin.catalago.application.castmember.update.DefaultUpdateCastMemberUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(controllers = CastMemberAPI.class)
public class CastMemberAPITest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DefaultCreateCastMemberUseCase createCastMemberUseCase;

    @MockBean
    private DefaultDeleteCastMemberUseCase deleteCastMemberUseCase;

    @MockBean
    private DefaultGetCastMemberByIdUseCase getCastMemberByIdUseCase;

    @MockBean
    private DefaultListCastMembersUseCase listCastMembersUseCase;

    @MockBean
    private DefaultUpdateCastMemberUseCase updateCastMemberUseCase;
}
