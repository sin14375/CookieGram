package cookiegram.ca.application.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@GetMapping("/dashboard")
    public String employeeDashboard() {
        return "employee/dashboard"; // View for employee dashboard
    }
    @GetMapping("/customers")
    public String customerReqest() {
        return "employee/customers";}

    @GetMapping("/inventory")
    public String checkInventory() {
        return "employee/inventory";
    }
    @GetMapping("/orders")
    public String viewOrders() {
        return "employee/orders";
    }

    @GetMapping("/tasks")
    public String viewTasks() {
        return "employee/tasks";
    }


    }
