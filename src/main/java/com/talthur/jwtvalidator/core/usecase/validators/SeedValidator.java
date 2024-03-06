package com.talthur.jwtvalidator.core.usecase.validators;

import com.talthur.jwtvalidator.core.model.JWT;
import org.springframework.stereotype.Component;

@Component
public class SeedValidator implements Validator {
    @Override
    public Boolean validate(JWT decodedJWT) {
        return decodedJWT.getSeed().map(this::validatePrimeNumber).orElse(false);
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
