package com.talthur.jwtvalidator.usecase.validators;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class NameValidator implements Validator {

    @Override
    public Boolean validate(DecodedJWT decodedJWT) {
        String regex = "^[^0-9]*$";
        Pattern pattern = Pattern.compile(regex);

        Optional<Claim> name = Optional.ofNullable(decodedJWT.getClaims().get("Name"));

        return name.map(claim -> {
            String claimConverted = claim.asString();
            return claimConverted.length() <= 256 && pattern.matcher(claimConverted).matches();
        }).orElse(false);
    }
}
