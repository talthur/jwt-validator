package com.talthur.jwtvalidator.model;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface Validator {

    Boolean validate(DecodedJWT decodedJWT);
}
