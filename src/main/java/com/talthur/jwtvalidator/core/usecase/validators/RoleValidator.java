package com.talthur.jwtvalidator.core.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.talthur.jwtvalidator.core.model.Claims;
import com.talthur.jwtvalidator.core.model.Roles;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleValidator implements Validator {

    private static Set<String> PERMITTED_ROLES = Arrays.stream(Roles.values())
            .map(Roles::getDescription).collect(Collectors.toSet());
    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        Optional<Claim> role = Optional.ofNullable(decodedJWT.getClaims().get(Claims.ROLE.getDescription()));

        return role.map(claim -> PERMITTED_ROLES.stream().anyMatch(claim.asString()::equals)).orElse(false);
    }
}
