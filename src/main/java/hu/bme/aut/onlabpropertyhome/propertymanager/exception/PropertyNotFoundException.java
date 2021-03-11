package hu.bme.aut.onlabpropertyhome.propertymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Property is not found")
public class PropertyNotFoundException extends RuntimeException{
    public PropertyNotFoundException() { }
}