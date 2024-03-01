package com.talthur.jwtvalidator.core.model;

import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Optional;
import java.util.Set;

public class JWT {

    private final String role;
    private final Integer seed;
    private final String name;
    private final Set<String> originalClaims;


    public JWT(DecodedJWT decodedJWT) {
        this.originalClaims = decodedJWT.getClaims().keySet();
        this.role = decodedJWT.getClaims().get(Claims.ROLE.getDescription()).asString();
        this.seed = decodedJWT.getClaims().get(Claims.SEED.getDescription()).asInt();
        this.name = decodedJWT.getClaims().get(Claims.NAME.getDescription()).asString();
    }

    public Optional<String> getRole() {
        return Optional.of(role);
    }

    public Optional<Integer> getSeed() {
        return Optional.of(seed);
    }

    public Optional<String> getName() {
        return Optional.of(name);
    }

    public Set<String> getOriginalClaims() {
        return originalClaims;
    }
}
