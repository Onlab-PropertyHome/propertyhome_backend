package hu.bme.aut.onlabpropertyhome.usermanager.controller;

import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.model.UserDTO;
import hu.bme.aut.onlabpropertyhome.usermanager.model.UserLoginDTO;
import hu.bme.aut.onlabpropertyhome.usermanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// user/add
// user/all
// user/id
//  PUT user/edit/id
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserRepository userRepository; // ideiglenes, amíg nincs autentikáció.

    public UserController(UserService userService){
        this.userService = userService;

    }




    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody User addNewUser(@RequestParam String name, @RequestParam String email){

        return  userService.addNewUser(name,email);
    }



    @PutMapping(path = "edit/{id}", produces = "application/json")
    public @ResponseBody User editUser (@PathVariable(value = "id") Integer id,
                                        @RequestParam String name,
                                        @RequestParam String email,
                                        @RequestParam String password,
                                        @RequestParam String tel) {

        return userService.editUser(id,name,email,password,tel);

    }



    @GetMapping(path="/{id}", produces = "application/json")
    public @ResponseBody
    User getUser (@PathVariable(value = "id") Integer id) {
        return userService.findUserByID(id);

    }



    @GetMapping("/all") // ide nem kell exception, majd kliens oldalon megnézzük h üres-e ami jön.
    public List<User> getAllUser() {
        return userService.findAll();
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

    @PostMapping(path="/login", produces = "application/json")
    public @ResponseBody String loginUser (@RequestBody UserLoginDTO userLoginDTO) {
        if (userRepository.findByEmail(userLoginDTO.getEmail()).isPresent()) {
            User user = userRepository.findByEmail(userLoginDTO.getEmail()).get();

            if (user.getPassword().equals(userLoginDTO.getPassword())) {
                return user.getId().toString();
            }

            return "err_wrong_credentials";
        }
        return "err_no_account_with_email";
    }


}
