package cookiegram.ca.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import cookiegram.ca.application.model.Cart;
import cookiegram.ca.application.security.CustomUserDetails;
import cookiegram.ca.application.service.CartService;

@Controller
public class CheckoutController {

    @Autowired
    private CartService cartService;

    @GetMapping("/checkout/review")
    public String reviewCheckout(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
        Cart cart = cartService.getCartByUser(userDetails.getUser());
        double subtotal = 0;
        int totalItems = 0;
        for (var item : cart.getItems()) {
            subtotal += item.getCookie().getPrice() * item.getQuantity();
            totalItems += item.getQuantity();
        }
        // Tax rate: 7% if fewer than 5 items; 12% otherwise.
        double taxRate = totalItems < 5 ? 0.07 : 0.12;
        double taxAmount = subtotal * taxRate;
        double total = subtotal + taxAmount;
        model.addAttribute("cart", cart);
        model.addAttribute("subtotal", subtotal);
        model.addAttribute("taxRate", taxRate * 100); // for display as percentage
        model.addAttribute("taxAmount", taxAmount);
        model.addAttribute("total", total);
        return "checkout";
    }

    @PostMapping("/checkout/confirm")
    public String confirmCheckout(@RequestParam(required = false) String agree,
                              @RequestParam String customerName,
                              @RequestParam String deliveryAddress,
                              @RequestParam String phoneNumber,
                              @RequestParam String postalCode,
                              @RequestParam String province,
                              @RequestParam(required = false) String specialRequests,
                              @AuthenticationPrincipal CustomUserDetails userDetails,
                              Model model) {
    if (agree == null || agree.isEmpty()) {
        model.addAttribute("error", "You must agree to the customer agreement policy to proceed.");
        return "checkout";
    }
                                
        // Process the order here, including delivery details.
        return "redirect:/checkout/success";
    }

    @GetMapping("/checkout/success")
    public String checkoutSuccess() {
        return "checkout-success";
    }
}
