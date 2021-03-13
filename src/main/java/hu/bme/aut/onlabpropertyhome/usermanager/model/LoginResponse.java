package hu.bme.aut.onlabpropertyhome.usermanager.model;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private final String jwt;

    public LoginResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
