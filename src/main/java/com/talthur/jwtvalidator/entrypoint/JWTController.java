package com.talthur.jwtvalidator.entrypoint;

import com.talthur.jwtvalidator.usecase.JWTValidatorUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/jwt")
public class JWTController {

    private final JWTValidatorUseCase jwtValidatorUseCase;

    public JWTController(JWTValidatorUseCase jwtValidatorUseCase) {
        this.jwtValidatorUseCase = jwtValidatorUseCase;
    }

    @PostMapping("/validate")
    public ResponseEntity<JWTValidatedDTO> validate(@RequestBody JWTDTO jwtDto) {
        Boolean isValid = jwtValidatorUseCase.validateJWT(jwtDto.token());
        return ResponseEntity.ok(new JWTValidatedDTO(jwtDto.token(), isValid));
    }

}
