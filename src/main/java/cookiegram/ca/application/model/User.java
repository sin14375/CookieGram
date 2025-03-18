package cookiegram.ca.application.model;

import java.util.Collection;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Represents a registered user in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This entity stores user details such as name, email, password,
 * and assigned roles, which determine access control in the system.
 */
@Entity
@Table(
    name = "user",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class User {

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The unique email of the user.
     */
    private String email;

    /**
     * The encrypted password of the user.
     */
    private String password;

    /**
     * The roles assigned to the user for authorization.
     * Users can have multiple roles such as "ROLE_ADMIN", "ROLE_EMPLOYEE", or "ROLE_USER".
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * Constructs a new user with the specified details.
     *
     * @param firstName The user's first name.
     * @param lastName  The user's last name.
     * @param email     The user's email address.
     * @param password  The user's encrypted password.
     * @param roles     The collection of roles assigned to the user.
     */
    public User(String firstName, String lastName, String email,
                String password, Collection<Role> roles) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.email     = email;
        this.password  = password;
        this.roles     = roles;
    }

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return The user ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user.
     *
     * @param id The user ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the user.
     *
     * @return The user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the user.
     *
     * @param firstName The user's first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the user.
     *
     * @return The user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the user.
     *
     * @param lastName The user's last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The user's email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the encrypted password of the user.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the encrypted password of the user.
     *
     * @param password The user's password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the roles assigned to the user.
     *
     * @return A collection of roles assigned to the user.
     */
    public Collection<Role> getRoles() {
        return roles;
    }

    /**
     * Sets the roles assigned to the user.
     *
     * @param roles The collection of roles assigned to the user.
     */
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
