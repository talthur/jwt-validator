package com.talthur.jwtvalidator.core.usecase.validators;

import com.talthur.jwtvalidator.core.model.JWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeedValidatorTest {

    @Mock
    private JWT jwt;


    private SeedValidator subject;

    @BeforeEach
    public void setup() {
        subject = new SeedValidator();
    }

    @Test
    public void shouldReturnTrueSeedIsAPrimeNumber() {
        when(jwt.getSeed()).thenReturn(Optional.of(7841));

        Boolean result = subject.validate(jwt);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenSeedIsNotAPrimeNumber() {
        when(jwt.getSeed()).thenReturn(Optional.of(8));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnTrueWhenSeedIsLowerThan2() {
        when(jwt.getSeed()).thenReturn(Optional.of(1));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

}