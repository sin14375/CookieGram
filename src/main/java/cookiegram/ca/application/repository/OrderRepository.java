package cookiegram.ca.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cookiegram.ca.application.model.Order;

/**
 * Repository interface for managing order-related database operations.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This repository provides methods for accessing and managing 
 * order entities in the database, enabling CRUD operations 
 * on customer orders.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
