package org.yearup.service;

import org.springframework.stereotype.Service;
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

    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile updateProfile(String username, Profile updatedProfile) {

        //Create a variable
        //Use JPA query to find the username from the repository and store it into the variable
            User user = userRepository.findByUsername(username);

            //Use the variable from above and use a getter to grab its ID and convert
            Profile existingProfile = profileRepository.findByUserId(user.getId());

            existingProfile.setFirstName(updatedProfile.getFirstName());
            existingProfile.setLastName(updatedProfile.getLastName());
            existingProfile.setEmail(updatedProfile.getEmail());
            existingProfile.setPhone(updatedProfile.getPhone());

            return profileRepository.save(existingProfile);

    }
}
