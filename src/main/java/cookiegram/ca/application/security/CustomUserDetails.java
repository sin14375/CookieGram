package cookiegram.ca.application.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import cookiegram.ca.application.model.User;

/**
 * Custom implementation of the Spring Security {@link UserDetails} interface.
 * This class is responsible for integrating the application's User model with 
 * Spring Security's authentication and authorization system.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This class provides user authentication details, including authorities, 
 * credentials, and account status.
 */
public class CustomUserDetails implements UserDetails {

    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructs a new CustomUserDetails object.
     * 
     * @param user the user entity containing account information
     * @param authorities the granted authorities (roles/permissions) of the user
     */
    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    /**
     * Retrieves the full name of the user.
     * 
     * @return the concatenated first and last name of the user
     */
    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }

    /**
     * Retrieves the underlying User entity.
     * 
     * @return the {@link User} object associated with this user details instance
     */
    public User getUser() {
        return user;
    }

    /**
     * Retrieves the authorities granted to the user.
     * 
     * @return a collection of {@link GrantedAuthority} representing user roles
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Retrieves the password of the user.
     * 
     * @return the encrypted password of the user
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retrieves the username of the user.
     * 
     * @return the email address of the user
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account is expired.
     * 
     * @return true, as accounts do not expire in this implementation
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is locked.
     * 
     * @return true, as accounts are not locked in this implementation
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are expired.
     * 
     * @return true, as credentials do not expire in this implementation
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * 
     * @return true, assuming all users are enabled by default
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
