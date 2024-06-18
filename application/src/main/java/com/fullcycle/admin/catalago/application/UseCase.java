package com.fullcycle.admin.catalago.application;


import com.fullcycle.admin.catalago.domain.category.Category;

public class UseCase {
    public Category execute(){
        return new Category();
    }
}