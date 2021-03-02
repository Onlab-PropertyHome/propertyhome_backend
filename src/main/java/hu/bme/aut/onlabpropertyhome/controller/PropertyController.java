package hu.bme.aut.onlabpropertyhome.controller;

import hu.bme.aut.onlabpropertyhome.model.*;
import hu.bme.aut.onlabpropertyhome.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@CrossOrigin
@RequestMapping
public class PropertyController {
    @Autowired
    private PropertyRepository propertyRepository;

    @GetMapping(path="/allProperty")
    public @ResponseBody Iterable<Property> getAllProperty() {
        return propertyRepository.findAll();
    }

    @GetMapping(path="/getProperty/{id}")
    public @ResponseBody
    Optional<Property> getProperty(@PathVariable(value = "id") Integer id) {
        return propertyRepository.findById(id);
    }

    @PutMapping(path="/editProperty/{id}")
    public @ResponseBody String editProperty(@PathVariable(value = "id") Integer id, PropertyDetails propertyDetails) {
        if (propertyRepository.findById(id).isPresent()) {
            Property old_property = propertyRepository.findById(id).get();
            old_property.setType(propertyDetails.getType());
            old_property.setState(propertyDetails.getState());
            old_property.setSize(propertyDetails.getSize());
            old_property.setRoomNumber(propertyDetails.getRoomNumber());

            propertyRepository.save(old_property);

            return "done";
        }

        return "err";
    }
}
