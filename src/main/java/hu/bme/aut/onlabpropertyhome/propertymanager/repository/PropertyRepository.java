package hu.bme.aut.onlabpropertyhome.propertymanager.repository;

import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Integer> { }
