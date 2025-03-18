package cookiegram.ca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cookiegram.ca.application.model.User;

/**
 * Repository interface for managing user-related database operations.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This repository provides methods for accessing and managing 
 * user entities in the database, including user retrieval by email.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Retrieves a user entity by their email address.
     * 
     * @param email the email of the user to retrieve
     * @return the User entity matching the given email, or null if not found
     */
    User findByEmail(String email);
}
