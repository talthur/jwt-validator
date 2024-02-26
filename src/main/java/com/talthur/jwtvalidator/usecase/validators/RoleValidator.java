package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class RoleValidator implements Validator {

    private static Set<String> PERMITTED_ROLES = Set.of("Admin", "Member", "External");
    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        Optional<Claim> role = Optional.ofNullable(decodedJWT.getClaims().get("Role"));

        return role.map(claim -> PERMITTED_ROLES.stream().anyMatch(claim.asString()::equals)).orElse(false);
    }
}
