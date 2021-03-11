package hu.bme.aut.onlabpropertyhome.propertymanager.controller;

import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import hu.bme.aut.onlabpropertyhome.propertymanager.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/property")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/all")
    public List<Property> getAllProperty() {
        return propertyService.findAllProperties();
    }

    @GetMapping("/{id}")
    public Property getProperty(@PathVariable(value = "id") Integer id) {
        return propertyService.findPropertyById(id);
    }

    @PutMapping("/edit/{id}")
    public Property editProperty(@PathVariable(value = "id") Integer id,
                                 @RequestParam Integer roomNumber, @RequestParam String type,
                                 @RequestParam String state, @RequestParam Integer size) {
        return propertyService.editProperty(id, roomNumber, type, state, size);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProperty(@PathVariable(value = "id") Integer id) {
        propertyService.deleteProperty(id);
    }
}