package com.fullcycle.admin.catalago.domain.category;

import com.fullcycle.admin.catalago.domain.Identifier;
import com.fullcycle.admin.catalago.domain.utils.IdUtils;

import java.util.Objects;
import java.util.UUID;

public class CategoryID extends Identifier {

    private final String value;

    private CategoryID(final String value){
        this.value =  Objects.requireNonNull(value);
    }

    public static CategoryID unique(){
        return CategoryID.from(IdUtils.uuid());
    }

    public static CategoryID from(final String anId){
        return new CategoryID(anId);
    }

    public static CategoryID from(final UUID anId){
        return new CategoryID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue());
    }
}
