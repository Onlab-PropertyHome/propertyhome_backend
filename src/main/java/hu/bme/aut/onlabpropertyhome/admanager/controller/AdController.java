package hu.bme.aut.onlabpropertyhome.admanager.controller;

import hu.bme.aut.onlabpropertyhome.admanager.exception.AdNotFoundException;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.service.AdService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/ad/all")
    public @ResponseBody List<Ad> findAllAds() {
        return adService.findAllAds();
    }

    @GetMapping("/ad/{id}")
    public @ResponseBody Ad getAd (@PathVariable(value = "id") Integer id) {
        return adService.findAdById(id);
    }

    @DeleteMapping("/ad/delete/{id}")
    public void deleteAd (@PathVariable(value = "id") Integer id) {
        adService.deleteAd(id);
    }

    @PutMapping("/ad/edit/{id}")
    public @ResponseBody Ad editAd (@PathVariable(value = "id") Integer id, @RequestParam String picture, @RequestParam String price,
                      @RequestParam String location, @RequestParam String details) {
        Ad ad = adService.editAd(id, picture, price, location, details);
        if (ad == null)
            throw new AdNotFoundException();

        return ad;
    }

    @PostMapping("/user/{id}/addad")
    public @ResponseBody Ad addAd (@PathVariable(value = "id") Integer id, @RequestParam String price, @RequestParam String location,
                     @RequestParam String details, @RequestParam Integer roomNumber, @RequestParam String type,
                     @RequestParam String state, @RequestParam Integer size) {
        return adService.addAd(id, price, location, details, roomNumber, type, state, size);
    }

    /*
    @PostMapping(path="/add")
    public @ResponseBody String addAd (@RequestParam Integer id,
                                       @RequestBody PropertyAd propertyAd) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            Ad ad = new Ad();
            ad.setDetails(propertyAd.getAdDetails().getDetails());
            ad.setLocation(propertyAd.getAdDetails().getLocation());
            ad.setPicture(propertyAd.getAdDetails().getPicture());
            ad.setPrice(propertyAd.getAdDetails().getPrice());
            ad.setUser(user);

            Property property = new Property();
            property.setRoomNumber(propertyAd.getPropertyDetails().getRoomNumber());
            property.setSize(propertyAd.getPropertyDetails().getSize());
            property.setState(propertyAd.getPropertyDetails().getState());
            property.setType(propertyAd.getPropertyDetails().getType());
            property.setAd(ad);

            ad.setProperty(property);
            user.addAd(ad);

            propertyRepository.save(property);
            adRepository.save(ad);
            userRepository.save(user);

            return "done";
        }

        return "err";
    }

    @GetMapping(path="/getAds")
    public @ResponseBody List<Ad> getAds(@RequestBody PropertyAd propertyAd) {
        List<Ad> list = adRepository.findAll();

        for (Ad a : list) {
            Property p = a.getProperty();

            if (propertyAd.getPropertyDetails().getRoomNumber() != null &&
                !p.getRoomNumber().equals(propertyAd.getPropertyDetails().getRoomNumber())) {
                list.remove(a);
                if (list.size() == 0)
                    break;
                continue;
            }
            if (propertyAd.getPropertyDetails().getSize() != null &&
                !p.getSize().equals(propertyAd.getPropertyDetails().getSize())) {
                list.remove(a);
                if (list.size() == 0)
                    break;
                continue;
            }
            if (propertyAd.getPropertyDetails().getState() != null &&
                !p.getState().equals(propertyAd.getPropertyDetails().getState())) {
                list.remove(a);
                if (list.size() == 0)
                    break;
                continue;
            }
            if (propertyAd.getPropertyDetails().getType() != null &&
                !p.getType().equals(propertyAd.getPropertyDetails().getType())) {
                list.remove(a);
                if (list.size() == 0)
                    break;
            }
        }

        return list;
    }
    */
}
