package com.talthur.jwtvalidator.core.model;

public enum Roles {

    NAME("Admin"),
    ROLE("Member"),
    SEED("External");

    private String description;

    Roles(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
