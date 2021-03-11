package hu.bme.aut.onlabpropertyhome.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Email is not found")
public class EmailNotFoundException extends RuntimeException { }
