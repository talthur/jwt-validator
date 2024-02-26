package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ClaimValidator implements Validator {

    private static final List<String> claims = List.of("Name", "Role", "Seed");

    @Override
    public Boolean validate(DecodedJWT decodedJWT) {

        Set<String> foundClaims = decodedJWT.getClaims().keySet();

        return foundClaims.containsAll(claims) && foundClaims.size() == 3;
    }
}
