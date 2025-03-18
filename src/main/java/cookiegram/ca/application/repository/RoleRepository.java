package cookiegram.ca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cookiegram.ca.application.model.Role;

/**
 * Repository interface for managing role-related database operations.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This repository provides methods for accessing and managing 
 * role entities in the database, allowing retrieval of roles 
 * by their names.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    
    /**
     * Retrieves a role entity by its name.
     * 
     * @param name the name of the role to retrieve
     * @return the Role entity matching the given name
     */
    Role findByName(String name);
}
