package chronity.triangle.validation.user;

import chronity.triangle.model.user.User;
import chronity.triangle.validation.Validations;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public final class UserValidator {
    private UserValidator() {}

    public static void validate(User requestBody) {
        if(! Validations.email(requestBody.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect email address provided.");

        if(! StringUtils.isAlphanumeric(requestBody.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect username provided.");
    }
}
