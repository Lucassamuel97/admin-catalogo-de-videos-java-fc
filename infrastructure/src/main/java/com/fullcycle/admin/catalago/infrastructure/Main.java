package com.fullcycle.admin.catalago.infrastructure;

import com.fullcycle.admin.catalago.application.UseCase;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        System.out.println(new UseCase().execute());
    }
}