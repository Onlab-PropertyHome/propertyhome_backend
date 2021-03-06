package hu.bme.aut.onlabpropertyhome.usermanager.controller;

import hu.bme.aut.onlabpropertyhome.admanager.model.AdSearch;
import hu.bme.aut.onlabpropertyhome.usermanager.model.User;
import hu.bme.aut.onlabpropertyhome.usermanager.model.UserDetails;
import hu.bme.aut.onlabpropertyhome.usermanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "User deleted successfully")
    public @ResponseBody void deleteUser(@PathVariable(value = "id") Integer id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "edit/{id}", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK, reason = "User updated successfully")
    public @ResponseBody User editUser (@PathVariable(value = "id") Integer id, @RequestParam String name,
                                        @RequestParam String email, @RequestParam String password,
                                        @RequestParam String tel, @RequestParam String picture) {
        return userService.editUser(id, name, email, password, tel, picture);
    }

    @GetMapping(path="/{id}", produces = "application/json")
    public @ResponseBody User getUser (@PathVariable(value = "id") Integer id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/all") // ide nem kell exception, majd kliens oldalon megnézzük h üres-e ami jön.
    public @ResponseBody List<User> getAllUser() {
        return userService.findAll();
    }

    @GetMapping("/{id}/favorites")
    public @ResponseBody List<Integer> getFavAds(@PathVariable(value = "id") Integer id) {
        return userService.getFavAds(id);
    }

    @PutMapping("/{id}/favorites/add")
    @ResponseStatus(value = HttpStatus.OK, reason = "Ad added to favorites successfully")
    public @ResponseBody User addAdToFav(@PathVariable(value = "id") Integer id, @RequestParam Integer ad_id) {
        return userService.addAdToFav(id, ad_id);
    }

    @PutMapping("/{id}/favorites/delete")
    @ResponseStatus(value = HttpStatus.OK, reason = "Ad removed from the favorites successfully")
    public @ResponseBody User deleteAdFromFav(@PathVariable(value = "id") Integer id, @RequestParam Integer ad_id) {
        return userService.deleteAdFromFav(id, ad_id);
    }

    @GetMapping("/find")
    public @ResponseBody UserDetails findUserByAdId(@RequestParam Integer ad_id) {
        return userService.findUserByAdId(ad_id);
    }

    @PutMapping("/{id}/savesearch")
    @ResponseStatus(value=HttpStatus.OK, reason = "Search saved")
    public void saveAdSearch(@PathVariable(value = "id")Integer id,
                                 @RequestParam(required = false) String location,
                                 @RequestParam(required = false) String price,
                                 @RequestParam(required = false) String type,
                                 @RequestParam(required = false) Integer size,
                                 @RequestParam(required = false) Integer roomNumber){
        userService.saveAdSearch(id,location,price,type,size,roomNumber);
    }




    @PutMapping("/{id}/removesearch/{searchid}")
    @ResponseStatus(value=HttpStatus.OK, reason = "Search removed")
    public void removeAdSearch(@PathVariable(value = "id")Integer id,
                               @PathVariable(value = "searchid") Integer searchid) {
        userService.removeAdSearch(id,searchid);
    }

    @GetMapping("/{id}/searches")
    public @ResponseBody List<AdSearch> getSearches(@PathVariable(value = "id") Integer id){
        return userService.getSearches(id);
    }
}
