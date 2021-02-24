package hu.bme.aut.onlabpropertyhome;

import hu.bme.aut.onlabpropertyhome.model.User;
import hu.bme.aut.onlabpropertyhome.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@CrossOrigin
@RequestMapping
public class MainController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @PutMapping(path="/edit/{id}", produces = "application/json")
    public @ResponseBody String editUser (@PathVariable(value = "id") Integer id,
                                          @RequestBody User user) {

        User old_user = userRepository.findById(id).get();
        old_user.setName(user.getName());
        old_user.setEmail(user.getEmail());

        userRepository.save(old_user);

        return "Updated";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return userRepository.findAll();
    }
}
