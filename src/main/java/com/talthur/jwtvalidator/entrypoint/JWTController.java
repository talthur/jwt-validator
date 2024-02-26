package com.talthur.jwtvalidator.entrypoint;

import com.talthur.jwtvalidator.core.usecase.JWTValidatorUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/jwt")
public class JWTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTController.class);

    private final JWTValidatorUseCase jwtValidatorUseCase;

    public JWTController(JWTValidatorUseCase jwtValidatorUseCase) {
        this.jwtValidatorUseCase = jwtValidatorUseCase;
    }

    @PostMapping("/validate")
    public ResponseEntity<JWTValidatedDTO> validate(@RequestBody JWTDTO jwtDto) {
        LOGGER.info("Validating JWT: {}", jwtDto.token());
        Boolean isValid = jwtValidatorUseCase.validateJWT(jwtDto.token());
        LOGGER.info("Validation result: {} for JWT token: {}",isValid, jwtDto.token());
        return ResponseEntity.ok(new JWTValidatedDTO(jwtDto.token(), isValid));
    }

}
