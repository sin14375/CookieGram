package cookiegram.ca.application.model;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents an order placed by a customer in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This entity maintains the details of an order including the customer, 
 * list of order items, and calculated properties such as total quantity 
 * and total amount due.
 */
@Entity
@Table(name = "orders")
public class Order {

    /**
     * The unique identifier for the order.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The customer who placed the order.
     * Represents a many-to-one relationship between users and orders.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customer;
    
    /**
     * The list of order items included in this order.
     * Represents a one-to-many relationship between an order and its items.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    /**
     * Default constructor.
     */
    public Order() {
    }

    /**
     * Constructs a new order with the specified customer and order items.
     *
     * @param customer The customer who placed the order.
     * @param items The list of items included in the order.
     */
    public Order(User customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    /**
     * Retrieves the unique identifier of the order.
     *
     * @return The order ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the order.
     *
     * @param id The order ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the customer who placed the order.
     *
     * @return The customer of the order.
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Sets the customer associated with the order.
     *
     * @param customer The customer to set.
     */
    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Retrieves the list of items in the order.
     *
     * @return The list of order items.
     */
    public List<OrderItem> getItems() {
        return items;
    }

    /**
     * Sets the list of items for the order.
     *
     * @param items The list of order items.
     */
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    /**
     * Calculates and returns the total quantity of all items in the order.
     *
     * @return The total quantity of items in the order.
     */
    public int getTotalQuantity() {
        return items.stream().mapToInt(OrderItem::getQuantity).sum();
    }

    /**
     * Calculates and returns the total amount due for the order.
     * This is computed by summing the price of each item multiplied by its quantity.
     *
     * @return The total amount due for the order.
     */
    public double getAmountDue() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
    }

    /**
     * Retrieves the full name of the customer who placed the order.
     *
     * @return The full name of the customer.
     */
    public String getCustomerFullName() {
        return customer.getFirstName() + " " + customer.getLastName();
    }
}
