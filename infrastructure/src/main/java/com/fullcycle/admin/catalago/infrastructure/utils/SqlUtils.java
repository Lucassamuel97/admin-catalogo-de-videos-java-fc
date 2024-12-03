package com.fullcycle.admin.catalago.infrastructure.utils;

public final class SqlUtils {

    private SqlUtils() {
    }

    public static String upper(final String term) {
        if (term == null) return null;
        return term.toUpperCase();
    }

    public static String like(final String term) {
        if (term == null) return null;
        return "%" + term + "%";
    }
}
