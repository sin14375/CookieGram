package cookiegram.ca.application.service;

import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.model.CartItem;
import cookiegram.ca.application.model.User;

public interface CartService {
    Cart getCartByUser(User user);
    Cart addItemToCart(User user, CartItem item);
    Cart removeItemFromCart(User user, Long cartItemId);
    Cart updateItemQuantity(User user, Long cartItemId, int quantity);
}
