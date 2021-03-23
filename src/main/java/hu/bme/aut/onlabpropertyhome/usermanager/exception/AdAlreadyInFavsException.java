package hu.bme.aut.onlabpropertyhome.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Ad is already in your favorite ads")
public class AdAlreadyInFavsException extends RuntimeException {
    public AdAlreadyInFavsException(){

    }
}
