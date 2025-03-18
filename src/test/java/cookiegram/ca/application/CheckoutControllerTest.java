package cookiegram.ca.application;

import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.model.CartItem;
import cookiegram.ca.application.model.Cookie;
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.security.CustomUserDetails;
import cookiegram.ca.application.service.CartService;
import cookiegram.ca.application.web.CheckoutController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.closeTo;

import java.util.Arrays;

public class CheckoutControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CartService cartService;

    @Mock
    private CustomUserDetails userDetails;

    @InjectMocks
    private CheckoutController checkoutController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(checkoutController).build();
        when(userDetails.getUser()).thenReturn(new User());
    }

    @Test
    void testReviewCheckout_Good() throws Exception {
        Cart cart = new Cart();
        Cookie cookie = new Cookie();
        cookie.setPrice(2.5);
        CartItem item = new CartItem(cookie, 2);
        cart.setItems(Arrays.asList(item));

        when(cartService.getCartByUser(any())).thenReturn(cart);

        mockMvc.perform(get("/checkout/review")
                .principal(() -> "user@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attribute("subtotal", 5.0))
                .andExpect(model().attribute("taxRate", closeTo(7.0, 0.0001)))
                .andExpect(model().attribute("total", 5.35));
    }

    @Test
    void testReviewCheckout_Bad_EmptyCart() throws Exception {
        Cart cart = new Cart();

        when(cartService.getCartByUser(any())).thenReturn(cart);

        mockMvc.perform(get("/checkout/review")
                .principal(() -> "user@example.com"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"))
                .andExpect(model().attributeExists("cart"))
                .andExpect(model().attribute("subtotal", 0.0))
                .andExpect(model().attribute("taxRate", closeTo(7.0, 0.0001)))
                .andExpect(model().attribute("total", 0.0));
    }

    @Test
    void testConfirmCheckout_Good() throws Exception {
        when(userDetails.getUser()).thenReturn(null);

        mockMvc.perform(post("/checkout/confirm")
                .param("agree", "on")
                .param("customerName", "John Doe")
                .param("deliveryAddress", "123 Main St")
                .param("phoneNumber", "1234567890")
                .param("postalCode", "A1B2C3")
                .param("province", "Ontario"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/checkout/success"));
    }

    @Test
    void testConfirmCheckout_Bad_NoAgreement() throws Exception {
        mockMvc.perform(post("/checkout/confirm")
                .param("agree", "")
                .param("customerName", "John Doe")
                .param("deliveryAddress", "123 Main St")
                .param("phoneNumber", "1234567890")
                .param("postalCode", "A1B2C3")
                .param("province", "Ontario"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "You must agree to the customer agreement policy to proceed."));
    }

    @Test
    void testCheckoutSuccess_Good() throws Exception {
        mockMvc.perform(get("/checkout/success"))
                .andExpect(status().isOk())
                .andExpect(view().name("checkout-success"));
    }
}
