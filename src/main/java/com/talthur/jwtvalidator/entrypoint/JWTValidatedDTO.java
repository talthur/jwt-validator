package com.talthur.jwtvalidator.entrypoint;

public record JWTValidatedDTO (String jwt, Boolean valid) {
}
