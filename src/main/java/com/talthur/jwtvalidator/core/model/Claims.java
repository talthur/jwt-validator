package com.talthur.jwtvalidator.core.model;

public enum Claims {

    NAME("Name"),
    ROLE("Role"),
    SEED("Seed");

    private String description;

    Claims(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
