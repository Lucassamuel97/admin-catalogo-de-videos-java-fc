package com.fullcycle.admin.catalago.application;

import com.fullcycle.admin.catalago.domain.castmember.CastMemberType;
import com.github.javafaker.Faker;

public class Fixture {

    private static final Faker FAKER = new Faker();

    public static String name() {
        return FAKER.name().fullName();
    }

    public static final class CastMembers {
        public static CastMemberType type() {
            return FAKER.options().option(
                    CastMemberType.ACTOR,
                    CastMemberType.DIRECTOR
            );
        }
    }
}
