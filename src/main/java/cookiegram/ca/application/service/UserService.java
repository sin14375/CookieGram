package cookiegram.ca.application.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    /**
     * Creates and saves a new User based on the provided registration data.
     */
    User save(UserRegistrationDto registrationDto);

    /**
     * Finds an existing User by their email address.
     */
    User findByEmail(String email);
}
