package com.fullcycle.admin.catalago.application.category.create;

import com.fullcycle.admin.catalago.domain.category.Category;
import com.fullcycle.admin.catalago.domain.category.CategoryGateway;
import com.fullcycle.admin.catalago.domain.validation.handler.Notification;

import static io.vavr.API.Left;
import static io.vavr.API.Try;
import io.vavr.control.Either;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase{

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway){
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, CreateCategoryOutput> execute(final CreateCategoryCommand aCommand) {

        final var aCategory = Category.newCategory(
                aCommand.name(),
                aCommand.description(),
                aCommand.isActive()
        );

        final var notification = Notification.create();

        aCategory.validate(notification);

        return notification.hasError() ? Left(notification) : create(aCategory);

    }

    private Either<Notification, CreateCategoryOutput> create(final Category aCategory) {
        return Try(() -> this.categoryGateway.create(aCategory))
                .toEither()
                .bimap(Notification::create, CreateCategoryOutput::from);
    }
}
