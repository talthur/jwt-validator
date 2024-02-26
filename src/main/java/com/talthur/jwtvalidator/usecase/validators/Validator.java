package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;

public interface Validator {

    Boolean validate(DecodedJWT decodedJWT);
}
