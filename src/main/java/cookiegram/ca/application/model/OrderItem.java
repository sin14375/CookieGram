package cookiegram.ca.application.model;

import jakarta.persistence.*;

/**
 * Represents an individual item within an order in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This entity stores details about the cookie ordered, its price at the time 
 * of the order, and the quantity purchased.
 */
@Entity
@Table(name = "order_item")
public class OrderItem {

    /**
     * The unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The price of the cookie at the time the order was placed.
     */
    private double price;

    /**
     * The quantity of this cookie ordered.
     */
    private int quantity;
    
    /**
     * The cookie associated with this order item.
     * Represents a many-to-one relationship, as multiple order items 
     * can reference the same cookie.
     */
    @ManyToOne
    @JoinColumn(name = "cookie_id")
    private Cookie cookie;

    /**
     * Default constructor.
     */
    public OrderItem() {
    }

    /**
     * Constructs a new order item with the specified cookie, quantity, and price.
     *
     * @param cookie The cookie being ordered.
     * @param quantity The quantity of the cookie ordered.
     * @param price The price of the cookie at the time of order.
     */
    public OrderItem(Cookie cookie, int quantity, double price) {
        this.cookie = cookie;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Retrieves the unique identifier of the order item.
     *
     * @return The order item ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the order item.
     *
     * @param id The order item ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the price of the cookie at the time of the order.
     *
     * @return The recorded price of the cookie.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the cookie at the time of the order.
     *
     * @param price The price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the quantity of this cookie ordered.
     *
     * @return The quantity ordered.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of this cookie ordered.
     *
     * @param quantity The quantity to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Retrieves the cookie associated with this order item.
     *
     * @return The cookie.
     */
    public Cookie getCookie() {
        return cookie;
    }

    /**
     * Sets the cookie associated with this order item.
     *
     * @param cookie The cookie to be set.
     */
    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }
}
