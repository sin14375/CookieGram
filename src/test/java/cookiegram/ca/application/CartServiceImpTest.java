package cookiegram.ca.application;

import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.model.CartItem;
import cookiegram.ca.application.model.Cookie;
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.repository.CartRepository;
import cookiegram.ca.application.service.CartServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.lang.reflect.Field;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CartServiceImpTest {

    @Autowired
    private CartServiceImpl cartService;

    private CartRepository cartRepositoryMock;

    private User user;
    private Cart cart;
    private CartItem cartItem;
    private Cookie cookie;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        cartRepositoryMock = Mockito.mock(CartRepository.class);

        cartService = new CartServiceImpl();
        Field field = CartServiceImpl.class.getDeclaredField("cartRepository");
        field.setAccessible(true);
        field.set(cartService, cartRepositoryMock);

        user = new User("John", "Doe", "john.doe@example.com", "password", null);
        cookie = new Cookie();
        cookie.setId(1L);
        cartItem = new CartItem(cookie, 2);
        cartItem.setId(1L);
        cart = new Cart(user);

        when(cartRepositoryMock.findByUser(user)).thenReturn(cart);
        when(cartRepositoryMock.save(any(Cart.class))).thenReturn(cart); 
    }

    @Test
    public void testGetCartByUser_CartExists() {
        Cart returnedCart = cartService.getCartByUser(user);
        verify(cartRepositoryMock, times(1)).findByUser(user);
        assertNotNull(returnedCart);
        assertEquals(user.getEmail(), returnedCart.getUser().getEmail());
    }

    @Test
    public void testGetCartByUser_CartDoesNotExist() {
        when(cartRepositoryMock.findByUser(user)).thenReturn(null);
        
        Cart newCart = cartService.getCartByUser(user);
        
        verify(cartRepositoryMock, times(1)).save(any(Cart.class));
        assertEquals(user.getEmail(), newCart.getUser().getEmail());
    }

    @Test
    public void testAddItemToCart_NewItem() {
        cartService.addItemToCart(user, cartItem);
        verify(cartRepositoryMock, times(1)).save(any(Cart.class));
    }

    @Test
    public void testAddItemToCart_ExistingItem() {
        CartItem existingItem = new CartItem(cookie, 1);
        cart.addItem(existingItem);
        cartItem.setQuantity(3);

        Cart updatedCart = cartService.addItemToCart(user, cartItem);
        
        verify(cartRepositoryMock, times(1)).save(updatedCart);
        assertEquals(4, existingItem.getQuantity());
    }

    @Test
    public void testRemoveItemFromCart() {
        cart.addItem(cartItem);

        Cart updatedCart = cartService.removeItemFromCart(user, cartItem.getId());

        verify(cartRepositoryMock, times(1)).save(updatedCart);
        assertFalse(updatedCart.getItems().contains(cartItem));
    }

    @Test
    public void testRemoveItemFromCart_ItemNotFound() {
        Cart updatedCart = cartService.removeItemFromCart(user, 999L);
        
        verify(cartRepositoryMock, times(1)).save(updatedCart);
        assertTrue(updatedCart.getItems().isEmpty());
    }

    @Test
    public void testUpdateItemQuantity() {
        cart.addItem(cartItem);
        
        Cart updatedCart = cartService.updateItemQuantity(user, cartItem.getId(), 5);
        
        verify(cartRepositoryMock, times(1)).save(updatedCart);
        assertEquals(5, cartItem.getQuantity());
    }

    @Test
    public void testUpdateItemQuantity_InvalidQuantity() {
        cart.addItem(cartItem);

        Cart updatedCart = cartService.updateItemQuantity(user, cartItem.getId(), 0);
        
        verify(cartRepositoryMock, times(1)).save(updatedCart);
        assertEquals(1, cartItem.getQuantity());
    }
}
