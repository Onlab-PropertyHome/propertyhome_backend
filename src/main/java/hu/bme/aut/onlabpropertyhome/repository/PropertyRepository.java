package hu.bme.aut.onlabpropertyhome.repository;

import hu.bme.aut.onlabpropertyhome.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> { }
