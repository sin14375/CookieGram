package cookiegram.ca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.model.User;

/**
 * Repository interface for managing cart-related database operations.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This repository provides methods for accessing and managing 
 * shopping cart entities in the database, including retrieving 
 * a cart by a specific user.
 */
public interface CartRepository extends JpaRepository<Cart, Long> {

    /**
     * Retrieves a cart associated with a specific user.
     *
     * @param user The user whose cart is to be retrieved.
     * @return The cart linked to the specified user, or null if none exists.
     */
    Cart findByUser(User user);
}
