package com.fullcycle.admin.catalago.application.category.retrieve.get;

import com.fullcycle.admin.catalago.domain.category.Category;
import com.fullcycle.admin.catalago.domain.category.CategoryGateway;
import com.fullcycle.admin.catalago.domain.category.CategoryID;
import com.fullcycle.admin.catalago.domain.exceptions.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase{
    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anIn) {
        final var anCategoryID = CategoryID.from(anIn);

        return this.categoryGateway.findById(anCategoryID)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(anCategoryID));
    }

    private Supplier<NotFoundException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(Category.class, anId);
    }
}
