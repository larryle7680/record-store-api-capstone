package org.yearup.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.models.Profile;
import org.yearup.models.User;
import org.yearup.repository.ProfileRepository;
import org.yearup.repository.UserRepository;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    //Return Profile
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(String username, Profile updatedProfile) {

        //Create a variable
        //Use JPA query to find the username from the repository and store it into the variable
            User user = userRepository.findByUsername(username);

            //Use the variable from above and use a getter to grab its ID and convert
            Profile existingProfile = profileRepository.findByUserId(user.getId());

            //Used a getter to get the data and set it into the new variables to return the object.
            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setEmail(updatedProfile.getEmail());
            existingProfile.setPhone(updatedProfile.getPhone());

            return profileRepository.save(existingProfile);

    }


    public Profile getProfile(String username) {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Profile profile = profileRepository.findByUserId(user.getId());

        if (profile == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");
        }

        return profile;
    }
}
