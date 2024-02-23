package com.talthur.jwtvalidator.usecase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class JWTValidatorUseCase {

    public Boolean validateJWT(String token) {

        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaims().keySet().containsAll(Set.of("Name", "Role", "Seed"));
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage()); //LOGGER
            return false;
        }

    }

}
