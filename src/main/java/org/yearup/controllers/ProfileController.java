package org.yearup.controllers;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
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



        @GetMapping
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<Profile> getProfile(Principal principal)
        {
            //Principal represents the user that is currently logged in
            //It'll grab the name that is currently logged in
            //Then, it'll go into profileService and .getProfile(Larry)
            Profile profile = profileService.getProfile(principal.getName());

            //Return 200 when getting the profile succeed
            return ResponseEntity.ok(profile);
        }


    @PutMapping()
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Profile> updateProfile(@RequestBody Profile updateProfile, Principal principal)
    {
        Profile profile = profileService.updateProfile(principal.getName(), updateProfile);

        return ResponseEntity.ok(profile);
    }

}
