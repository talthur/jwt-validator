package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ClaimValidator implements Validator {

    public static final Set<String> PERMITTED_CLAIMS = Set.of("Name", "Role", "Seed");

    @Override
    public Boolean validate(DecodedJWT decodedJWT) {

        Set<String> foundClaims = decodedJWT.getClaims().keySet();

        return foundClaims.containsAll(PERMITTED_CLAIMS) && foundClaims.size() == 3;
    }
}
