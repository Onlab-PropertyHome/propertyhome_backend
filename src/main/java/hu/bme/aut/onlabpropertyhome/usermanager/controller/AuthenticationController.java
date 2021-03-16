package hu.bme.aut.onlabpropertyhome.usermanager.controller;

import hu.bme.aut.onlabpropertyhome.usermanager.model.AuthResponse;
import hu.bme.aut.onlabpropertyhome.usermanager.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public @ResponseBody
    AuthResponse login(@RequestParam String email, @RequestParam String password) throws Exception {
        return userDetailsService.login(email, password);
    }

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    AuthResponse register(@RequestParam String name, @RequestParam String email,
                          @RequestParam String password, @RequestParam String tel) {
        return userDetailsService.register(name, email, password, tel);
    }
}
