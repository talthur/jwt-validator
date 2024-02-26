package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Set;

import static com.talthur.jwtvalidator.usecase.validators.ClaimValidator.PERMITTED_CLAIMS;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClaimValidatorTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DecodedJWT decodedJWT;

    private ClaimValidator subject;

    @BeforeEach
    public void setup() {
        subject = new ClaimValidator();
    }

    @Test
    public void shouldReturnTrueWhenValidClaimSizeAndClaimNames() {
        when(decodedJWT.getClaims().keySet()).thenReturn(PERMITTED_CLAIMS);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimSizeAbove3() {
        when(decodedJWT.getClaims().keySet()).thenReturn(Set.of("1", "2", "3", "4"));

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenInvalidClaim() {
        when(decodedJWT.getClaims().keySet()).thenReturn(Set.of("Banana", "Role", "Seed"));

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenEmptyClaims() {
        when(decodedJWT.getClaims()).thenReturn(Collections.emptyMap());

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);

    }

}