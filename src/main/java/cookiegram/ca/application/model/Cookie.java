package cookiegram.ca.application.model;

import jakarta.persistence.*;

/**
 * Represents a cookie product in the Cookiegram application.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 *
 * This entity defines the attributes of a cookie including its name, price,
 * stock availability, and image URL.
 */
@Entity
@Table(name = "cookie")
public class Cookie {

    /**
     * The unique identifier for the cookie.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The name of the cookie.
     */
    private String name;
    
    /**
     * The price of the cookie.
     */
    private double price;
    
    /**
     * The URL of the cookie image.
     */
    private String imageUrl;
    
    /**
     * The stock level indicating the available quantity of this cookie.
     */
    private int stock;

    /**
     * Default constructor.
     */
    public Cookie() {
    }

    /**
     * Constructs a new cookie with specified details.
     *
     * @param name The name of the cookie.
     * @param price The price of the cookie.
     * @param imageUrl The URL of the cookie's image.
     * @param stock The available stock quantity.
     */
    public Cookie(String name, double price, String imageUrl, int stock) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stock = stock;
    }

    /**
     * Retrieves the unique identifier of the cookie.
     *
     * @return The cookie ID.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the cookie.
     *
     * @param id The cookie ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the cookie.
     *
     * @return The cookie name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the cookie.
     *
     * @param name The name of the cookie.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the cookie.
     *
     * @return The cookie price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the cookie.
     *
     * @param price The new price of the cookie.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the image URL of the cookie.
     *
     * @return The URL of the cookie image.
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the image URL for the cookie.
     *
     * @param imageUrl The URL of the cookie's image.
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * Retrieves the available stock quantity of the cookie.
     *
     * @return The stock quantity.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the available stock quantity for the cookie.
     *
     * @param stock The stock quantity to set.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
}
