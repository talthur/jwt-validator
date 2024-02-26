package com.talthur.jwtvalidator.core.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.talthur.jwtvalidator.core.usecase.validators.NameValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.talthur.jwtvalidator.core.usecase.validators.ClaimValidator.PERMITTED_CLAIMS;
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
    public static final String CLAIM = "Name";

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DecodedJWT decodedJWT;
    @Mock
    private Claim claim;

    private NameValidator subject;

    @BeforeEach
    public void setup() {
        subject = new NameValidator();
    }

    @Test
    public void shouldReturnTrueWhenClaimNameDoesNotHaveAnyNumber() {
        when(decodedJWT.getClaims().keySet()).thenReturn(PERMITTED_CLAIMS);
        when(decodedJWT.getClaims().get(CLAIM)).thenReturn(claim);
        when(claim.asString()).thenReturn(VALID_NAME);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimNameDoesHaveNumber() {
        when(decodedJWT.getClaims().keySet()).thenReturn(PERMITTED_CLAIMS);
        when(decodedJWT.getClaims().get(CLAIM)).thenReturn(claim);
        when(claim.asString()).thenReturn(INVALID_NAME);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimNameOverSizeLimit() {
        when(decodedJWT.getClaims().keySet()).thenReturn(PERMITTED_CLAIMS);
        when(decodedJWT.getClaims().get(CLAIM)).thenReturn(claim);
        when(claim.asString()).thenReturn(INVALID_NAME_OVERSIZED);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenClaimNameIsNull() {
        when(decodedJWT.getClaims().keySet()).thenReturn(PERMITTED_CLAIMS);
        when(decodedJWT.getClaims().get(CLAIM)).thenReturn(null);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

}