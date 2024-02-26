package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NameValidator implements Validator {

    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        String name = decodedJWT.getClaims().get("Name").asString();

        String regex = "^[^0-9]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches() && name.length() <= 256;
    }
}
