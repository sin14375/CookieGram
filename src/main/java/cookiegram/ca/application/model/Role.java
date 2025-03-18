package cookiegram.ca.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a user role in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This entity defines different user roles (e.g., ADMIN, EMPLOYEE, CUSTOMER)
 * that control access to various parts of the system.
 */
@Entity
@Table(name = "role")
public class Role {

    /**
     * The unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role (e.g., "ROLE_ADMIN", "ROLE_EMPLOYEE", "ROLE_USER").
     */
    private String name;

    /**
     * Default constructor.
     */
    public Role() {
    }

    /**
     * Constructs a new role with the specified name.
     *
     * @param name The name of the role.
     */
    public Role(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique identifier of the role.
     *
     * @return The role ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the role.
     *
     * @param id The role ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the role.
     *
     * @return The role name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the role.
     *
     * @param name The role name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
