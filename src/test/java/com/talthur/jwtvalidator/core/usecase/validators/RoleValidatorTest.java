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
class RoleValidatorTest {

    @Mock
    private JWT jwt;

    private RoleValidator subject;

    @BeforeEach
    public void setup() {
        subject = new RoleValidator();
    }

    @Test
    public void shouldReturnTrueWhenRoleIsAdmin() {
        when(jwt.getRole()).thenReturn(Optional.of("Admin"));

        Boolean result = subject.validate(jwt);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenRoleIsMember() {
        when(jwt.getRole()).thenReturn(Optional.of("Member"));

        Boolean result = subject.validate(jwt);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnTrueWhenRoleIsExternal() {
        when(jwt.getRole()).thenReturn(Optional.of("External"));

        Boolean result = subject.validate(jwt);

        Assertions.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenRoleIsInvalid() {
        when(jwt.getRole()).thenReturn(Optional.of("Banana"));

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseWhenRoleIsEmpty() {
        when(jwt.getRole()).thenReturn(Optional.empty());

        Boolean result = subject.validate(jwt);

        Assertions.assertFalse(result);
    }


}