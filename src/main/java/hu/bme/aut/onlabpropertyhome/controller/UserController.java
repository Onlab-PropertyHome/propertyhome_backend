package hu.bme.aut.onlabpropertyhome.controller;

import hu.bme.aut.onlabpropertyhome.model.User;
import hu.bme.aut.onlabpropertyhome.model.UserDTO;
import hu.bme.aut.onlabpropertyhome.model.UserLoginDTO;
import hu.bme.aut.onlabpropertyhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/addNewUser")
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        if (userRepository.findByEmail(email).isPresent())
            return "err_email_already_in_use";
        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "done";
    }

    @PostMapping(path="/register", produces = "application/json")
    public @ResponseBody String registerUser (@RequestBody UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent())
            return "err_email_already_in_use";

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setTel(userDTO.getTel());

        userRepository.save(user);

        return "done";
    }

    @GetMapping(path="/login", produces = "application/json")
    public @ResponseBody String loginUser (@RequestBody UserLoginDTO userLoginDTO) {
        if (userRepository.findByEmail(userLoginDTO.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(userLoginDTO.getEmail()).get();

            if (user.getPassword().equals(userLoginDTO.getPassword())) {
                return "token";
            }

            return "err_wrong_credentials";
        }
        return "err_no_account_with_email";
    }

    @PutMapping(path="/edit")
    public @ResponseBody String editUserByEmail (@RequestBody UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            User old_user = userRepository.findByEmail(userDTO.getEmail()).get();
            old_user.setName(userDTO.getName());
            old_user.setEmail(userDTO.getEmail());
            old_user.setPassword(userDTO.getPassword());
            old_user.setTel(userDTO.getTel());

            userRepository.save(old_user);

            return "done";
        }

        return "err";
    }

    @PutMapping(path="/editUser/{id}", produces = "application/json")
    public @ResponseBody String editUser (@PathVariable(value = "id") Integer id,
                                          @RequestBody User user) {
        if (userRepository.findById(id).isPresent()) {
            User old_user = userRepository.findById(id).get();
            old_user.setName(user.getName());
            old_user.setEmail(user.getEmail());
            old_user.setAds(user.getAds());
            old_user.setPassword(user.getPassword());
            old_user.setTel(user.getTel());

            userRepository.save(old_user);

            return "done";
        }

        return "err";
    }

    @DeleteMapping(path="/deleteAllUser")
    public @ResponseBody String deleteAllUser() {
        userRepository.deleteAll();
        return "done";
    }

    @GetMapping(path="/getUser/{id}", produces = "application/json")
    public @ResponseBody
    Optional<User> getUser (@PathVariable(value = "id") Integer id) {
        return userRepository.findById(id);
    }

    @GetMapping(path="/getUserDTO/{id}", produces = "application/json")
    public @ResponseBody
    UserDTO getUserDTO (@PathVariable(value = "id") Integer id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            return new UserDTO(user);
        }

        return null;
    }

    @GetMapping(path="/allUser")
    public @ResponseBody Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping(path="/getUserId")
    public @ResponseBody String getUserId (@RequestBody UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(userDTO.getEmail()).get();
            return user.getId().toString();
        }

        return "err";
    }
}
