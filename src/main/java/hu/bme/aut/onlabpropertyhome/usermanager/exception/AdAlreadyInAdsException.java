package hu.bme.aut.onlabpropertyhome.usermanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You cannot add your ad into your favorite ads")
public class AdAlreadyInAdsException extends RuntimeException {
    public AdAlreadyInAdsException(){

    }
}
