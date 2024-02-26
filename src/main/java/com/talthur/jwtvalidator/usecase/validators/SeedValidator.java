package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Optional;

public class SeedValidator implements Validator {
    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        Optional<Claim> seed = Optional.ofNullable(decodedJWT.getClaims().get("Seed"));

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
