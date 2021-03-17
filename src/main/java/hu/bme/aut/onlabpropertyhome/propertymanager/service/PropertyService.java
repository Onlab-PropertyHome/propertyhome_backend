package hu.bme.aut.onlabpropertyhome.propertymanager.service;

import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import hu.bme.aut.onlabpropertyhome.propertymanager.exception.PropertyNotFoundException;
import hu.bme.aut.onlabpropertyhome.propertymanager.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> findAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        if (properties.isEmpty())
            throw new PropertyNotFoundException();

        return properties;
    }

    public Property findPropertyById(Integer id) {
        return propertyRepository.findById(id).orElseThrow(PropertyNotFoundException::new);
    }

    public Property editProperty(Integer id, Integer roomNumber, String type, String state, Integer size) {
        if (propertyRepository.findById(id).isPresent()) {
            Property old_property = propertyRepository.findById(id).get();
            if (type != null && !type.equals(""))
                old_property.setType(type);
            if (state !=null && !state.equals(""))
                old_property.setState(state);
            if (size != null)
                old_property.setSize(size);
            if (roomNumber!=null)
                old_property.setRoomNumber(roomNumber);

            return propertyRepository.save(old_property);
        }

        throw new PropertyNotFoundException();
    }

    public void deleteProperty(Integer id) {
        propertyRepository.deleteById(id);
    }
}