package hu.bme.aut.onlabpropertyhome.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Login failed: Wrong user credentials")
public class WrongCredentialsException extends RuntimeException { }
