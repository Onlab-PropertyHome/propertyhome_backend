package hu.bme.aut.onlabpropertyhome.admanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Ad is not found")
public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException() { }
}