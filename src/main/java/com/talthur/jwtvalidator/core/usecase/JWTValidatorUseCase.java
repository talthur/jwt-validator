package com.talthur.jwtvalidator.core.usecase;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.talthur.jwtvalidator.core.model.JWT;
import com.talthur.jwtvalidator.core.usecase.validators.Validator;
import io.micrometer.observation.annotation.Observed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Observed(name = "validator")
public class JWTValidatorUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTValidatorUseCase.class);

    private final Set<Validator> validators;

    public JWTValidatorUseCase(Set<Validator> validators) {
        this.validators = validators;
    }

    public Boolean validateJWT(String token) {

        try {
            DecodedJWT decodedJWT = com.auth0.jwt.JWT.decode(token);
            return validators.stream()
                    .map(validator -> validator.validate(new JWT(decodedJWT)))
                    .filter(result -> !result).findFirst().orElse(true);
        } catch (JWTVerificationException e) {
            LOGGER.error("Exception at decoding JWT. Cause: {}", e.getMessage());
            return false;
        }

    }

}
