package hu.bme.aut.onlabpropertyhome.usermanager.service;

import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailAlreadyInUseException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.UserNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class  UserService {
    private final UserRepository userRepository;

    // encoder
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserRepository u){
        this.userRepository = u;
    }

    public User editUser(Integer id,String name, String email, String password, String tel) {
        if (userRepository.findById(id).isPresent()) {
            if (userRepository.findByEmail(email).isPresent())
                throw new EmailAlreadyInUseException();

            User old_user = userRepository.findById(id).get();

            if (name != null && !name.equals(""))
                old_user.setName(name);
            if (email != null && !email.equals(""))
                old_user.setEmail(email);
            // encoding the password
            if (password != null && !password.equals(""))
                old_user.setPassword(bcryptEncoder.encode(password));
            if (tel != null && !tel.equals(""))
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

    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return;
        }

        throw new UserNotFoundException();
    }
}
