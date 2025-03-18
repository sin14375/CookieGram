package cookiegram.ca.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cookiegram.ca.application.repository.CookieRepository;
import cookiegram.ca.application.repository.OrderRepository;
import cookiegram.ca.application.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CookieRepository cookieRepository;
    
    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        long totalOrders = orderRepository.count();
        long totalUsers = userRepository.count();
        long totalProducts = cookieRepository.count();
        
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalProducts", totalProducts);
        model.addAttribute("cookies", cookieRepository.findAll());
        
        return "admin/dashboard";
    }

    @GetMapping("/orders")
    public String customerPlacedOrders() {
        return "admin/orders";
    }
   
    @GetMapping("/products")
    public String viewAvailaleProducts() {
        return "admin/products";
    }
    @GetMapping("/users")
    public String viewRegisteredUsers() {
        return "admin/users";
    }
    @GetMapping("/inventory")
    public String adminCheckInventory() {
        return "admin/inventory";
    }
}
 
