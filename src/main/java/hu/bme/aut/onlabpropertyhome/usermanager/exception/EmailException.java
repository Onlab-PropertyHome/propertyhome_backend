package hu.bme.aut.onlabpropertyhome.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Email is already in use")
public class EmailException extends RuntimeException {
    public EmailException(){

    }
}
