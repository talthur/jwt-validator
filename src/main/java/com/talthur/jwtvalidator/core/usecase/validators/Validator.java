package com.talthur.jwtvalidator.core.usecase.validators;


import com.talthur.jwtvalidator.core.model.JWT;

public interface Validator {

    Boolean validate(JWT decodedJWT);
}
