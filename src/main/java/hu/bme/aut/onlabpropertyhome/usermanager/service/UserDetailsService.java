package hu.bme.aut.onlabpropertyhome.usermanager.service;

import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailAlreadyInUseException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.WrongCredentialsException;
import hu.bme.aut.onlabpropertyhome.usermanager.model.AuthResponse;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import hu.bme.aut.onlabpropertyhome.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), new ArrayList<>()
            );
        }

        throw new EmailNotFoundException();
    }

    // login
    public AuthResponse login(String email, String password) throws Exception {
        /*
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email, password
                    )
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password", e);
        }*/

        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();

            if (bcryptEncoder.matches(password, user.getPassword())) {
                // generating token
                final UserDetails userDetails = loadUserByUsername(email);
                final String jwt = jwtTokenUtil.generateToken(userDetails, user);

                return new AuthResponse(jwt);
            }

            throw new WrongCredentialsException();
        }

        throw new EmailNotFoundException();
    }

    // register
    public AuthResponse register(String name, String email, String password, String tel) {
        if (userRepository.findByEmail(email).isPresent())
            throw new EmailAlreadyInUseException();

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        // encoding the password
        user.setPassword(bcryptEncoder.encode(password));
        user.setTel(tel);

        User registered = userRepository.save(user);

        // generating token
        final UserDetails userDetails = loadUserByUsername(email);
        final String jwt = jwtTokenUtil.generateToken(userDetails, registered);

        return new AuthResponse(jwt);
    }
}
