package hu.bme.aut.onlabpropertyhome.admanager.controller;

import hu.bme.aut.onlabpropertyhome.admanager.exception.AdNotFoundException;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.service.AdService;

import hu.bme.aut.onlabpropertyhome.model.PropertyAd;
import hu.bme.aut.onlabpropertyhome.propertymanager.model.Property;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Ad deleted successfully")
    public void deleteAd (@PathVariable(value = "id") Integer id) {
        adService.deleteAd(id);
    }

    @PutMapping("/ad/edit/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Ad edited successfully")
    public @ResponseBody Ad editAd (@PathVariable(value = "id") Integer id, @RequestParam String picture, @RequestParam String price,
                      @RequestParam String location, @RequestParam String details) {
        return adService.editAd(id, picture, price, location, details);
    }

    @PostMapping("/user/{id}/addad")
    @ResponseStatus(value = HttpStatus.CREATED, reason = "Ad added successfully")
    public @ResponseBody Ad addAd (@PathVariable(value = "id") Integer id, @RequestParam String price, @RequestParam String location,
                     @RequestParam String details, @RequestParam Integer roomNumber, @RequestParam String type,
                     @RequestParam String state, @RequestParam Integer size) {
        return adService.addAd(id, price, location, details, roomNumber, type, state, size);
    }

    @GetMapping("/ad/find")
    public @ResponseBody List<Ad> findAds(@RequestParam Integer roomNumber, @RequestParam String type,
                                          @RequestParam String state, @RequestParam Integer size) {
        return adService.findAds(roomNumber, type, state, size);
    }
}
