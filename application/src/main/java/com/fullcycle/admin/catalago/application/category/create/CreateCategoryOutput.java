package com.fullcycle.admin.catalago.application.category.create;

import com.fullcycle.admin.catalago.domain.category.Category;
import com.fullcycle.admin.catalago.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final CategoryID anId){
        return new CreateCategoryOutput(anId);
    }

    public static CreateCategoryOutput from(final Category aCategory){
        return new CreateCategoryOutput(aCategory.getId());
    }
}
