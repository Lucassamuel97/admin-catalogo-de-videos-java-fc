package com.fullcycle.admin.catalago.domain;

import com.fullcycle.admin.catalago.application.UseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UseCaseTest {

    @Test
    public void testCreateUseCase(){
        UseCase caso = new UseCase();
        Assertions.assertNotNull(caso);

        Assertions.assertNotNull(caso.execute());
    }

}
