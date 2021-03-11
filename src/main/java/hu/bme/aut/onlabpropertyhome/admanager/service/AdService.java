package hu.bme.aut.onlabpropertyhome.admanager.service;

import hu.bme.aut.onlabpropertyhome.admanager.exception.AdNotFoundException;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.repository.AdRepository;
import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import hu.bme.aut.onlabpropertyhome.propertymanager.repository.PropertyRepository;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.UserNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdService {
    private final AdRepository adRepository;
    /*
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private UserRepository userRepository;*/

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdService(AdRepository adRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    // TODO
    // public Ad addAd(Ad ad) { ... }

    public List<Ad> findAllAds() {
        List<Ad> ads = adRepository.findAll();
        if (ads.isEmpty())
            throw new AdNotFoundException();
        return ads;
    }

    public Ad findAdById(Integer id) {
        return adRepository.findById(id)
                .orElseThrow(AdNotFoundException::new);
    }

    public void deleteAd(Integer id) {
        adRepository.deleteById(id);
    }

    public Ad editAd(Integer id, String picture, String price, String location, String details) {
        if (adRepository.findById(id).isPresent()) {
            Ad old_ad = adRepository.findById(id).get();
            old_ad.setPicture(picture);
            old_ad.setPrice(price);
            old_ad.setLocation(location);
            old_ad.setDetails(details);

            return adRepository.save(old_ad);
        }

        return null;
    }

    public Ad addAd(Integer id, String price, String location, String details, Integer roomNumber, String type, String state, Integer size) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            Ad ad = new Ad();
            ad.setDetails(details);
            ad.setLocation(location);
            // TODO
            //ad.setPicture( ... );
            ad.setPrice(price);
            ad.setUser(user);

            Property property = new Property();
            property.setRoomNumber(roomNumber);
            property.setSize(size);
            property.setState(state);
            property.setType(type);
            property.setAd(ad);

            ad.setProperty(property);
            user.addAd(ad);

            propertyRepository.save(property);
            adRepository.save(ad);
            userRepository.save(user);
            propertyRepository.save(property);

            return ad;
        }

        throw new UserNotFoundException();
    }
}