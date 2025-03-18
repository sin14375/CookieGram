package cookiegram.ca.application.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a shopping cart for a user in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This entity maintains the relationship between a user and their selected 
 * items in the cart. A cart belongs to one user and contains multiple cart items.
 */
@Entity
@Table(name = "cart")
public class Cart {

    /**
     * The unique identifier for the cart.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The user associated with this cart.
     */
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * A list of items currently in the cart.
     */
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Cart() {
    }

    /**
     * Constructs a cart associated with a specific user.
     *
     * @param user The user who owns the cart.
     */
    public Cart(User user) {
        this.user = user;
    }

    /**
     * Retrieves the unique identifier of the cart.
     *
     * @return The cart ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the cart.
     *
     * @param id The cart ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the user associated with this cart.
     *
     * @return The user owning the cart.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with this cart.
     *
     * @param user The user to be assigned to this cart.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Retrieves the list of items in the cart.
     *
     * @return The list of cart items.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Sets the list of items in the cart.
     *
     * @param items The list of cart items.
     */
    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    /**
     * Adds an item to the cart.
     *
     * @param item The cart item to be added.
     */
    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
    }

    /**
     * Removes an item from the cart.
     *
     * @param item The cart item to be removed.
     */
    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
    }
}
