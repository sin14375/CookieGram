package cookiegram.ca.application.model;

import jakarta.persistence.*;

/**
 * Represents an item within a shopping cart in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * Each cart item corresponds to a specific cookie and maintains the quantity 
 * selected by the customer. It is linked to a cart and a cookie.
 */
@Entity
@Table(name = "cart_item")
public class CartItem {

    /**
     * The unique identifier for the cart item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The cart to which this item belongs.
     */
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    /**
     * The cookie associated with this cart item.
     */
    @ManyToOne
    @JoinColumn(name = "cookie_id")
    private Cookie cookie;

    /**
     * The quantity of the cookie in the cart.
     */
    private int quantity;

    /**
     * Default constructor.
     */
    public CartItem() {
    }

    /**
     * Constructs a cart item with a specified cookie and quantity.
     *
     * @param cookie  The cookie associated with the cart item.
     * @param quantity The quantity of the cookie.
     */
    public CartItem(Cookie cookie, int quantity) {
        this.cookie = cookie;
        this.quantity = quantity;
    }

    /**
     * Retrieves the unique identifier of the cart item.
     *
     * @return The cart item ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the cart item.
     *
     * @param id The cart item ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the cart associated with this item.
     *
     * @return The cart containing this item.
     */
    public Cart getCart() {
        return cart;
    }

    /**
     * Sets the cart for this item.
     *
     * @param cart The cart to associate with this item.
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Retrieves the cookie associated with this cart item.
     *
     * @return The cookie in the cart item.
     */
    public Cookie getCookie() {
        return cookie;
    }

    /**
     * Sets the cookie for this cart item.
     *
     * @param cookie The cookie to associate with this cart item.
     */
    public void setCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    /**
     * Retrieves the quantity of the cookie in the cart.
     *
     * @return The quantity of the cookie.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the cookie in the cart.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
