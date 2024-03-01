package com.talthur.jwtvalidator.core.usecase.validators;

import com.talthur.jwtvalidator.core.model.JWT;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class NameValidator implements Validator {

    public static final int MAX_SIZE = 256;

    @Override
    public Boolean validate(JWT decodedJWT) {
        String regex = "^[^0-9]*$";
        Pattern pattern = Pattern.compile(regex);

        return decodedJWT.getName()
                .map(claim -> claim.length() <= MAX_SIZE && pattern.matcher(claim).matches()).orElse(false);
    }
}
