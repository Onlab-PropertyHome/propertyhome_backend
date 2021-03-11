package hu.bme.aut.onlabpropertyhome.usermanager.service;


import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.UserNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository u){
        this.userRepository = u;
    }


    public User addNewUser(String name, String email) {

        if (userRepository.findByEmail(email).isPresent())
            throw new EmailException();

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return n;
    }

    public User editUser(Integer id,String name, String email, String password, String tel){

        if (userRepository.findById(id).isPresent()) {
            User old_user = userRepository.findById(id).get();
            old_user.setName(name);
            old_user.setEmail(email);
            old_user.setPassword(password);
            old_user.setTel(tel);

            return userRepository.save(old_user);
        }
        throw new UserNotFoundException();
    }

    public User findUserByID(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
