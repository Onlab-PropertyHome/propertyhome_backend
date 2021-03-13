package hu.bme.aut.onlabpropertyhome.propertymanager.controller;

import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import hu.bme.aut.onlabpropertyhome.propertymanager.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/property")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/all")
    public @ResponseBody List<Property> getAllProperty() {
        return propertyService.findAllProperties();
    }

    @GetMapping("/{id}")
    public @ResponseBody Property getProperty(@PathVariable(value = "id") Integer id) {
        return propertyService.findPropertyById(id);
    }

    @PutMapping("/edit/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Property updated successfully")
    public @ResponseBody Property editProperty(@PathVariable(value = "id") Integer id,
                                 @RequestParam Integer roomNumber, @RequestParam String type,
                                 @RequestParam String state, @RequestParam Integer size) {
        return propertyService.editProperty(id, roomNumber, type, state, size);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Property deleted successfully")
    public void deleteProperty(@PathVariable(value = "id") Integer id) {
        propertyService.deleteProperty(id);
    }
}