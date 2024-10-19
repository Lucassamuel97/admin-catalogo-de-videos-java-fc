package com.fullcycle.admin.catalago.application;


import com.fullcycle.admin.catalago.domain.category.Category;

public abstract class UseCase <IN, OUT>{

    public abstract OUT execute(IN anIn);

}