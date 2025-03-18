package cookiegram.ca.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import cookiegram.ca.application.security.CustomUserDetails;
import cookiegram.ca.application.service.CartService;

@Controller
@RequestMapping("/customer")
public class CustomerDashboardController {

    @Autowired
    private CartService cartService;
    
    @GetMapping("/dashboard")
    public String customerDashboard(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        int cartCount = cartService.getCartByUser(userDetails.getUser()).getItems().size();
        model.addAttribute("cartCount", cartCount);
        return "customer/dashboard";
    }
}
