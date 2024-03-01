package com.talthur.jwtvalidator.core.usecase.validators;

import com.talthur.jwtvalidator.core.model.JWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static com.talthur.jwtvalidator.core.usecase.validators.ClaimValidator.PERMITTED_CLAIMS;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClaimValidatorTest {

    @Mock
    private JWT jwt;

    private ClaimValidator subject;

    @BeforeEach
    public void setup() {
        subject = new ClaimValidator();
    }

    @Test
    public void shouldReturnTrueWhenValidClaimSizeAndClaimNames() {
        when(jwt.getOriginalClaims()).thenReturn(PERMITTED_CLAIMS);

        Boolean result = subject.validate(jwt);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimSizeAbove3() {
        when(jwt.getOriginalClaims()).thenReturn(Set.of("1", "2", "3", "4"));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenInvalidClaim() {
        when(jwt.getOriginalClaims()).thenReturn(Set.of("Banana", "Role", "Seed"));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenEmptyClaims() {
        when(jwt.getOriginalClaims()).thenReturn(Collections.EMPTY_SET);

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);

    }

}