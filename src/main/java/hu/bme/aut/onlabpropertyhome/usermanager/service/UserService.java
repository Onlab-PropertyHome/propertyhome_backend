package hu.bme.aut.onlabpropertyhome.usermanager.service;

import hu.bme.aut.onlabpropertyhome.admanager.exception.AdNotFoundException;
import hu.bme.aut.onlabpropertyhome.admanager.model.Ad;
import hu.bme.aut.onlabpropertyhome.admanager.repository.AdRepository;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.AdAlreadyInAdsException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.AdAlreadyInFavsException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.EmailAlreadyInUseException;
import hu.bme.aut.onlabpropertyhome.usermanager.exception.UserNotFoundException;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.model.UserDetails;
import hu.bme.aut.onlabpropertyhome.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class  UserService {
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    // encoder
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    public UserService(UserRepository u, AdRepository adRepository){
        this.userRepository = u;
        this.adRepository = adRepository;
    }

    public User editUser(Integer id,String name, String email, String password, String tel, String picture) {
        if (userRepository.findById(id).isPresent()) {
            if (userRepository.findByEmail(email).isPresent())
                throw new EmailAlreadyInUseException();

            User old_user = userRepository.findById(id).get();

            if (name != null && !name.equals(""))
                old_user.setName(name);
            if (email != null && !email.equals(""))
                old_user.setEmail(email);
            // encoding the password
            if (password != null && !password.equals(""))
                old_user.setPassword(bcryptEncoder.encode(password));
            if (tel != null && !tel.equals(""))
                old_user.setTel(tel);
            if (picture != null && !picture.equals(""))
                old_user.setPicture(picture);

            return userRepository.save(old_user);
        }
        throw new UserNotFoundException();
    }

    public User findUserByID(Integer id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            if(!user.getAds().isEmpty()){
                for(Ad a : user.getAds()){
                    adRepository.delete(a);
                }
            }
            userRepository.deleteById(id);
            return;
        }

        throw new UserNotFoundException();
    }

    public List<Integer> getFavAds(Integer id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            return user.getFavAds();
        }

        throw new UserNotFoundException();
    }

    public User addAdToFav(Integer id, Integer ad_id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();
            List<Integer> favAds = user.getFavAds();

            if (favAds.contains(ad_id))
                throw new AdAlreadyInFavsException();

            List<Ad> ads = user.getAds();
            for (Ad ad : ads) {
                if (ad.getAd_id().equals(ad_id))
                    throw new AdAlreadyInAdsException();
            }

            user.addAdToFav(ad_id);
            return userRepository.save(user);
        }

        throw new UserNotFoundException();
    }

    public User deleteAdFromFav(Integer id, Integer ad_id) {
        if (userRepository.findById(id).isPresent()) {
            User user = userRepository.findById(id).get();

            List<Integer> fav_ads = user.getFavAds();
            if (fav_ads.contains(ad_id)) {
                user.removeAdFromFav(ad_id);
                return userRepository.save(user);
            }

            throw new AdNotFoundException();
        }

        throw new UserNotFoundException();
    }

    public UserDetails findUserByAdId(Integer ad_id) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            List<Ad> ads = user.getAds();

            if (ads.isEmpty())
                continue;

            for (Ad ad : ads) {
                if (ad.getAd_id().equals(ad_id))
                    return new UserDetails(user.getName(), user.getPicture(), user.getTel());
            }

            throw new AdNotFoundException();
        }

        throw new UserNotFoundException();
    }
}
