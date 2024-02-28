package com.talthur.jwtvalidator.core.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.talthur.jwtvalidator.core.model.Claims;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClaimValidator implements Validator {

    public static final Set<String> PERMITTED_CLAIMS = Arrays.stream(Claims.values())
            .map(Claims::getDescription).collect(Collectors.toSet());

    @Override
    public Boolean validate(DecodedJWT decodedJWT) {

        Set<String> foundClaims = decodedJWT.getClaims().keySet();

        return foundClaims.containsAll(PERMITTED_CLAIMS) && foundClaims.size() == PERMITTED_CLAIMS.size();
    }
}
