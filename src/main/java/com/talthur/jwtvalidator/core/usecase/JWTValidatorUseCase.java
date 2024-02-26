package com.talthur.jwtvalidator.core.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.talthur.jwtvalidator.core.usecase.validators.Validator;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JWTValidatorUseCase {


    private final Set<Validator> validators;

    public JWTValidatorUseCase(Set<Validator> validators) {
        this.validators = validators;
    }

    public Boolean validateJWT(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return validators.stream()
                    .map(validator -> validator.validate(decodedJWT))
                    .filter(result -> !result).findFirst().orElse(true);
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage()); //LOGGER
            return false;
        }

    }

}
