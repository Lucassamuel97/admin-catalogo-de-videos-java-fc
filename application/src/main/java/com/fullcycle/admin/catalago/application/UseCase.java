package com.fullcycle.admin.catalago.application;


import com.fullcycle.admin.catalago.domain.category.Category;

public class UseCase {
    public Category execute(){
         var expectedName = "Filmes";
         var expectedDescription = "A categoria mais assistida";
         var expectedIsActive = true;

        return Category.newCategory(expectedName, expectedDescription, expectedIsActive);
    }
}