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
class NameValidatorTest {

    public static final String VALID_NAME = "Toninho Araujo";
    public static final String INVALID_NAME = "Toninh0 4raujo";
    public static final String INVALID_NAME_OVERSIZED =
            """
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin molestie sed est vitae tempor.
            In bibendum nisl id mattis vehicula. Sed suscipit, turpis vel aliquam dictum, neque tortor sollicitudin 
            dolor, ac ultrices urna leo non massa. Vestibulum mattis arcu eget. """;

    @Mock
    private JWT jwt;

    private NameValidator subject;

    @BeforeEach
    public void setup() {
        subject = new NameValidator();
    }

    @Test
    public void shouldReturnTrueWhenClaimNameDoesNotHaveAnyNumber() {
        when(jwt.getName()).thenReturn(Optional.of(VALID_NAME));

        Boolean result = subject.validate(jwt);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimNameDoesHaveNumber() {
        when(jwt.getName()).thenReturn(Optional.of(INVALID_NAME));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimNameOverSizeLimit() {
        when(jwt.getName()).thenReturn(Optional.of(INVALID_NAME_OVERSIZED));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimNameIsEmpty() {
        when(jwt.getName()).thenReturn(Optional.empty());

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

}