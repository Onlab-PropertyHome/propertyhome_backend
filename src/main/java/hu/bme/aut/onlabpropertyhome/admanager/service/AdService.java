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
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdService(AdRepository adRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.adRepository = adRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

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
            if(price != null && !price.equals(""))
            old_ad.setPrice(price);
            if (location != null && !location.equals(""))
            old_ad.setLocation(location);
            if(details != null && !details.equals(""))
            old_ad.setDetails(details);

            return adRepository.save(old_ad);
        }

        throw new AdNotFoundException();
    }

    public Ad addAd(Integer id, String price, String location, String details, Integer roomNumber, String type, String state, Integer size, String picture, Double lat, Double lng) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            Ad ad = new Ad();
            ad.setDetails(details);
            ad.setLocation(location);
            ad.setPicture(picture);
            ad.setPrice(price);
            ad.setUser(user);

            Property property = new Property();
            property.setRoomNumber(roomNumber);
            property.setSize(size);
            property.setState(state);
            property.setType(type);
            property.setLat(lat);
            property.setLng(lng);
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

    public List<Ad> findAds(Integer roomNumber, String type, Integer size, String price) {
        List<Ad> list = adRepository.findAll();

        for (Ad a : list) {
            Property p = a.getProperty();

            if (roomNumber != null && !p.getRoomNumber().equals(roomNumber)) {
                list.remove(a);
                if (list.size() == 0)
                    break;
            }
            if (size != null && p.getSize() < size) {
                list.remove(a);
                if (list.size() == 0)
                    break;
            }
            if (price  != null) {
                int pprice = Integer.parseInt(a.getPrice());
                switch(price){
                    case "0-20000":
                        if(!(pprice >= 0 && pprice <= 20000))
                            list.remove(a);
                        break;

                    case "20000-40000":
                        if(!(pprice >= 20000 && pprice <= 40000))
                            list.remove(a);
                        break;

                    case "40000-60000":
                        if(!(pprice >= 40000 && pprice <= 60000))
                            list.remove(a);
                        break;

                    case "60000-80000":
                        if(!(pprice >= 60000 && pprice <= 80000))
                            list.remove(a);
                        break;

                    case "80000-100000":
                        if(!(pprice >= 80000 && pprice <= 100000))
                            list.remove(a);
                        break;

                    case "100000 ":
                        if((pprice <= 100000))
                            list.remove(a);
                        break;
                    default: break;
                }
                if (list.size() == 0)
                    break;
            }
            if (type != null && !p.getType().equals(type)) {
                list.remove(a);
                if (list.size() == 0)
                    break;
            }
        }

        if (list.isEmpty())
            throw new AdNotFoundException();

        return list;
    }
}