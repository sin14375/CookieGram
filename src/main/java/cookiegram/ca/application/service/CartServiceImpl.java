package cookiegram.ca.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.model.CartItem;
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    
    @Override
    public Cart getCartByUser(User user) {
        Cart cart = cartRepository.findByUser(user);
        if (cart == null) {
            cart = new Cart(user);
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public Cart addItemToCart(User user, CartItem item) {
        Cart cart = getCartByUser(user);
        // Check if the cookie is already in the cart; if so, update the quantity.
        boolean updated = false;
        for (CartItem existingItem : cart.getItems()) {
            if (existingItem.getCookie().getId().equals(item.getCookie().getId())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                updated = true;
                break;
            }
        }
        if (!updated) {
            cart.addItem(item);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeItemFromCart(User user, Long cartItemId) {
        Cart cart = getCartByUser(user);
        cart.getItems().removeIf(item -> item.getId().equals(cartItemId));
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateItemQuantity(User user, Long cartItemId, int quantity) {
        if (quantity < 1) {
            quantity = 1;
        }
        Cart cart = getCartByUser(user);
        for (CartItem item : cart.getItems()) {
            if (item.getId().equals(cartItemId)) {
                item.setQuantity(quantity);
                break;
            }
        }
        return cartRepository.save(cart);
    }
}
