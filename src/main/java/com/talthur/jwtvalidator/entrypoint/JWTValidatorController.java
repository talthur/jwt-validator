package com.talthur.jwtvalidator.entrypoint;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.talthur.jwtvalidator.usecase.JWTValidatorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTValidatorController {

    private final JWTValidatorUseCase jwtValidatorUseCase;

    public JWTValidatorController(JWTValidatorUseCase jwtValidatorUseCase) {
        this.jwtValidatorUseCase = jwtValidatorUseCase;
    }

    @PostMapping("/jwt/validate")
    public ResponseEntity<Boolean> validate(@RequestBody JWTDto jwtDto) {
        return ResponseEntity.ok(jwtValidatorUseCase.validateJWT(jwtDto.token()));
    }

}
