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
class RoleValidatorTest {

    public static final String ROLE = "Role";
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private DecodedJWT decodedJWT;

    @Mock
    private Claim claim;

    private RoleValidator subject;

    @BeforeEach
    public void setup() {
        subject = new RoleValidator();
    }

    @Test
    public void shouldReturnTrueWhenRoleIsAdmin() {
        when(decodedJWT.getClaims().get(ROLE)).thenReturn(claim);
        when(claim.asString()).thenReturn("Admin");

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenRoleIsMember() {
        when(decodedJWT.getClaims().get(ROLE)).thenReturn(claim);
        when(claim.asString()).thenReturn("Member");

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenRoleIsExternal() {
        when(decodedJWT.getClaims().get(ROLE)).thenReturn(claim);
        when(claim.asString()).thenReturn("External");

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenRoleIsInvalid() {
        when(decodedJWT.getClaims().get(ROLE)).thenReturn(claim);
        when(claim.asString()).thenReturn("Banana");

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenRoleIsNull() {
        when(decodedJWT.getClaims().get(ROLE)).thenReturn(null);

        Boolean result = subject.validate(decodedJWT);

        Assertions.assertFalse(result);
    }


}