package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;

public class SeedValidator implements Validator {
    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        Integer seed = decodedJWT.getClaims().get("Seed").asInt();

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
