package com.fullcycle.admin.catalago.application;

import com.fullcycle.admin.catalago.IntegrationTest;
import com.fullcycle.admin.catalago.application.category.create.CreateCategoryUseCase;
import com.fullcycle.admin.catalago.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
public class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void test(){
        Assertions.assertNotNull(useCase);
        Assertions.assertNotNull(categoryRepository);
    }

}
