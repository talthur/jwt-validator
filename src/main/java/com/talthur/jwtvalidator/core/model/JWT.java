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
        this.seed = Integer.valueOf(decodedJWT.getClaims().get(Claims.SEED.getDescription()).asString());
        this.name = decodedJWT.getClaims().get(Claims.NAME.getDescription()).asString();
    }

    public Optional<String> getRole() {
        return Optional.ofNullable(role);
    }

    public Optional<Integer> getSeed() {
        return Optional.ofNullable(seed);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Set<String> getOriginalClaims() {
        return originalClaims;
    }
}
