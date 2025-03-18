package cookiegram.ca.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.model.CartItem;
import cookiegram.ca.application.model.Cookie;
import cookiegram.ca.application.security.CustomUserDetails;
import cookiegram.ca.application.service.CartService;
import cookiegram.ca.application.repository.CookieRepository;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private CookieRepository cookieRepository;

    // When a customer adds an item, they remain on the dashboard.
    @PostMapping("/add")
    public String addToCart(@AuthenticationPrincipal CustomUserDetails userDetails,
                            @RequestParam Long cookieId,
                            @RequestParam(defaultValue = "1") int quantity) {
        Cookie cookie = cookieRepository.findById(cookieId)
            .orElseThrow(() -> new IllegalArgumentException("Invalid cookie ID"));
        CartItem item = new CartItem(cookie, quantity);
        cartService.addItemToCart(userDetails.getUser(), item);
        return "redirect:/customer/dashboard";
    }

    // View the cart page.
    @GetMapping("/view")
    public String viewCart(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Cart cart = cartService.getCartByUser(userDetails.getUser());
        model.addAttribute("cart", cart);
        return "cart";
    }
    
    // Update quantity for a cart item.
    @PostMapping("/update")
    public String updateCartItem(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @RequestParam Long cartItemId,
                                 @RequestParam int quantity) {
        cartService.updateItemQuantity(userDetails.getUser(), cartItemId, quantity);
        return "redirect:/cart/view";
    }

    // Remove an item from the cart.
    @PostMapping("/remove")
    public String removeFromCart(@AuthenticationPrincipal CustomUserDetails userDetails,
                                 @RequestParam Long cartItemId) {
        cartService.removeItemFromCart(userDetails.getUser(), cartItemId);
        return "redirect:/cart/view";
    }
}
