package hu.bme.aut.onlabpropertyhome.admanager.repository;

import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Integer> { }
