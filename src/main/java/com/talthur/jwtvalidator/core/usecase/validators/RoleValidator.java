package com.talthur.jwtvalidator.core.usecase.validators;

import com.talthur.jwtvalidator.core.model.JWT;
import com.talthur.jwtvalidator.core.model.Roles;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleValidator implements Validator {

    private static final Set<String> PERMITTED_ROLES = Arrays.stream(Roles.values())
            .map(Roles::getDescription).collect(Collectors.toSet());

    @Override
    public Boolean validate(JWT decodedJWT) {
        return decodedJWT.getRole().map(claim -> PERMITTED_ROLES.stream().anyMatch(claim::equals)).orElse(false);
    }
}
