package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeedValidatorTest {

    public static final String SEED = "Seed";
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DecodedJWT decodedJWT;

    @Mock
    private Claim claim;

    private SeedValidator subject;

    @BeforeEach
    public void setup() {
        subject = new SeedValidator();
    }

    @Test
    public void shouldReturnTrueSeedIsAPrimeNumber() {
        when(decodedJWT.getClaims().get(SEED)).thenReturn(claim);
        when(claim.asInt()).thenReturn(7841);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenSeedIsNotAPrimeNumber() {
        when(decodedJWT.getClaims().get(SEED)).thenReturn(claim);
        when(claim.asInt()).thenReturn(8);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenSeedIsLowerThan2() {
        when(decodedJWT.getClaims().get(SEED)).thenReturn(claim);
        when(claim.asInt()).thenReturn(1);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

}