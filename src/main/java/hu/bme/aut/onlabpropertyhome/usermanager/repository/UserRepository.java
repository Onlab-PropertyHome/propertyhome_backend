package hu.bme.aut.onlabpropertyhome.usermanager.repository;

import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String Email);
}
