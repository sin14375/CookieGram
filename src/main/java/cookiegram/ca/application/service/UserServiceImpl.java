package cookiegram.ca.application.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cookiegram.ca.application.model.Role;
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.repository.RoleRepository;
import cookiegram.ca.application.repository.UserRepository;
import cookiegram.ca.application.web.dto.UserRegistrationDto;
import cookiegram.ca.application.security.CustomUserDetails;  

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Saves a new user with an assigned role based on the email.
     * and assigned to the user.
     */
    @Override
    public User save(UserRegistrationDto registrationDto) {
        // Default role is ROLE_USER
        String roleName = "ROLE_USER";
        
        // If the email is "admin@example.com", assign ROLE_ADMIN
        if ("admin@example.com".equalsIgnoreCase(registrationDto.getEmail())) {
            roleName = "ROLE_ADMIN";
        }
        // If the email matches one of the employee addresses, assign ROLE_EMPLOYEE
        else if ("employee1@example.com".equalsIgnoreCase(registrationDto.getEmail())
              || "employee2@example.com".equalsIgnoreCase(registrationDto.getEmail())
              || "employee3@example.com".equalsIgnoreCase(registrationDto.getEmail())) {
            roleName = "ROLE_EMPLOYEE";
        }
        
        // Lookup the role from the database; if it doesn't exist, create and save it.
        Role roleEntity = roleRepository.findByName(roleName);
        if (roleEntity == null) {
            roleEntity = roleRepository.save(new Role(roleName));
        }
        
        // Create and save the new user with the existing role entity.
        User user = new User(
            registrationDto.getFirstName(),
            registrationDto.getLastName(),
            registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()),
            Arrays.asList(roleEntity)
        );
        
        return userRepository.save(user);
    }

    /**
     * Saves a new user with explicitly provided roles.
     */
    public User saveWithRoles(UserRegistrationDto registrationDto, List<Role> roles) {
        User user = new User(
            registrationDto.getFirstName(),
            registrationDto.getLastName(),
            registrationDto.getEmail(),
            passwordEncoder.encode(registrationDto.getPassword()),
            roles
        );
        return userRepository.save(user);
    }
    
    /**
     * Finds a user by email.
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Loads a user by username (email) for Spring Security authentication.
     * Returns a CustomUserDetails instance that exposes the user's full name.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        
        // Return an instance of CustomUserDetails instead of the default Spring Security User.
        return new CustomUserDetails(user, mapRolesToAuthorities(user.getRoles()));
    }
    
    /**
     * Transforms a collection of Role entities into GrantedAuthority objects.
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
    }
}
