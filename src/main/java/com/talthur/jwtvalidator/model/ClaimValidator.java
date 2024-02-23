package com.talthur.jwtvalidator.model;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimValidator implements Validator {

    private static final List<String> claims = List.of("Name", "Role", "Seed");

    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims().keySet().containsAll(claims);
    }
}
