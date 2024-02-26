package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Objects;

public class RoleValidator implements Validator {
    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        String role = decodedJWT.getClaims().get("Role").asString();
        return Objects.equals(role, "Admin")
                || Objects.equals(role, "Member")
                || Objects.equals(role, "External");
    }
}
