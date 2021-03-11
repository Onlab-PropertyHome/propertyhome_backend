package hu.bme.aut.onlabpropertyhome.admanager.service;

import hu.bme.aut.onlabpropertyhome.admanager.exception.AdNotFoundException;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdService {
    private final AdRepository adRepository;

    @Autowired
    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
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
}