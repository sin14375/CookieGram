package cookiegram.ca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cookiegram.ca.application.model.Cookie;

/**
 * Repository interface for managing cookie-related database operations.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This repository provides methods for accessing and managing 
 * cookie entities in the database, allowing CRUD operations 
 * on the cookie inventory.
 */
public interface CookieRepository extends JpaRepository<Cookie, Long> {
}
