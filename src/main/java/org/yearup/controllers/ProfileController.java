package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;
import org.yearup.models.User;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@CrossOrigin
public class ProfileController {

UserDao userDao;
ProfileDao profileDao;


@Autowired
public ProfileController(UserDao userDao, ProfileDao profileDao){
    this.userDao = userDao;
    this.profileDao = profileDao;
}

@GetMapping
    public Profile viewProfile(Principal principal){

    String userName = principal.getName();
    User user = userDao.getByUserName(userName);
    int userId = user.getId();

    return profileDao.getProfileById(userId);

    }

// mapping for update
    @PutMapping
    public @ResponseBody Profile updateProfile(Principal principal, @RequestBody Profile profile){
        String userName = principal.getName();
        User user = userDao.getByUserName(userName);
        int userId = user.getId();

       return profileDao.update(userId,profile);
    }

    // make a create method and use post mapping (mimic add new product logic)

}
