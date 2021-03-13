package hu.bme.aut.onlabpropertyhome.usermanager.controller;

import hu.bme.aut.onlabpropertyhome.usermanager.model.LoginResponse;
import hu.bme.aut.onlabpropertyhome.usermanager.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
public class AuthenticationController {
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public @ResponseBody LoginResponse createToken(@RequestParam String email, @RequestParam String password) throws Exception {
        return userDetailsService.createToken(email, password);
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "User successfully registered")
    public @ResponseBody LoginResponse register(@RequestParam String name, @RequestParam String email,
                                                @RequestParam String password, @RequestParam String tel) {
        return userDetailsService.register(name, email, password, tel);
    }
}
