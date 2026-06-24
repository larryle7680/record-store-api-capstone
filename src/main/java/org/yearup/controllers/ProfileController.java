package org.yearup.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.repository.UserRepository;
import org.yearup.service.ProfileService;

import java.security.Principal;

//Adding a RestController, CrossOrigin, RequestMapping
@RestController
@CrossOrigin
@RequestMapping("/profile")


public class ProfileController {

    //Inject the repository
    private final UserRepository userRepository;
    private final ProfileService profileService;

    //Constructor for the repository
    public ProfileController(UserRepository userRepository, ProfileService profileService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
    }


    @GetMapping()
    @PreAuthorize("isAuthenticated()")
    public User getProfile(Principal principal)
    {
        //Find the username, then put it into a variable to be able to return it later
        User user = userRepository.findByUsername(principal.getName());

        //Throw this exception if user isn't found
        if (user == null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return user;
    }

    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    public Profile updateProfile(@RequestBody Profile updateProfile, Principal principal)
    {
        return profileService.updateProfile(
                principal.getName(),
                updateProfile);
    }

}
