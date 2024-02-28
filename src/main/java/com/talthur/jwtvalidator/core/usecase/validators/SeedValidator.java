package com.talthur.jwtvalidator.core.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.talthur.jwtvalidator.core.model.Claims;

import java.util.Optional;

public class SeedValidator implements Validator {
    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        Optional<Claim> seed = Optional.ofNullable(decodedJWT.getClaims().get(Claims.SEED.getDescription()));

        return seed.map(claim -> validatePrimeNumber(claim.asInt())).orElse(false);

    }

    private Boolean validatePrimeNumber(Integer seed) {
        if (seed < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(seed); i++) {
            if (seed % i == 0) {
                return false;
            }
        }

        return true;
    }
}
