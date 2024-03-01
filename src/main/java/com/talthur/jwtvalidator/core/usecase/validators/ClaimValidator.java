package com.talthur.jwtvalidator.core.usecase.validators;

import com.talthur.jwtvalidator.core.model.Claims;
import com.talthur.jwtvalidator.core.model.JWT;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClaimValidator implements Validator {

    public static final Set<String> PERMITTED_CLAIMS = Arrays.stream(Claims.values())
            .map(Claims::getDescription).collect(Collectors.toSet());

    @Override
    public Boolean validate(JWT decodedJWT) {
        return decodedJWT.getOriginalClaims().containsAll(PERMITTED_CLAIMS)
                && decodedJWT.getOriginalClaims().size() == PERMITTED_CLAIMS.size();
    }
}
