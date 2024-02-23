package com.talthur.jwtvalidator.entrypoint;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTValidatorController {

    @PostMapping("/jwt/validate")
    public ResponseEntity<Boolean> validate(@RequestBody JWTDto jwtDto) {

        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("baeldung"))
                .withIssuer("Baeldung")
                .build();

        try {
            DecodedJWT decodedJWT = verifier.verify(jwtDto.token());
        } catch (JWTVerificationException e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.ok(true);
    }

}
