package hu.bme.aut.onlabpropertyhome.usermanager.controller;

import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User deleted successfully")
    public @ResponseBody void deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "edit/{ id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK, reason = "User updated successfully")
    public @ResponseBody User editUser (@PathVariable(value = "id") Integer id,
                                        @RequestParam String name, @RequestParam String email,
                                        @RequestParam String password, @RequestParam String tel) {
        return userService.editUser(id,name,email,password,tel);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public @ResponseBody User getUser (@PathVariable(value = "id") Integer id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/all") // ide nem kell exception, majd kliens oldalon megnézzük h üres-e ami jön.
    public @ResponseBody List<User> getAllUser() {
        return userService.findAll();
    }
}
