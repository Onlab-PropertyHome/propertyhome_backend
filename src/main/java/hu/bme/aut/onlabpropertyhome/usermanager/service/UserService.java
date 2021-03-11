package hu.bme.aut.onlabpropertyhome.usermanager.service;

import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailAlreadyInUseException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.UserNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.WrongCredentialsException;
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
            throw new EmailAlreadyInUseException();

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return n;
    }

    public User editUser(Integer id,String name, String email, String password, String tel) {
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

    public User deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }

        throw new UserNotFoundException();
    }

    public User login(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            if (user.getPassword().equals(password))
                return user;

            throw new WrongCredentialsException();
        }

        throw new EmailNotFoundException();
    }

    public User register(String name, String email, String password, String tel) {
        if (userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyInUseException();

        User user = new User(name, email, password, tel);
        return userRepository.save(user);
    }
}
