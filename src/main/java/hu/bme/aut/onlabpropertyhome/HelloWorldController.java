package hu.bme.aut.onlabpropertyhome;

import hu.bme.aut.onlabpropertyhome.model.User;
import hu.bme.aut.onlabpropertyhome.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String helloWorld() {
        User nu = new User();
        nu.setEmail("asd");
        nu.setName("asdd");

        userRepository.save(nu);

        return "Hello World";
    }
}
