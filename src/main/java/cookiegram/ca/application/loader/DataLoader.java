package cookiegram.ca.application.loader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import cookiegram.ca.application.service.UserService;
import cookiegram.ca.application.web.dto.UserRegistrationDto;

/**
 * Loads initial user data into the database upon application startup.
 *
 * Contributors:
 * - Destiny
 * - Krimy
 * - Hashdeep
 * - Disha
 * 
 * This class ensures that a default admin and employee accounts are created 
 * when the application starts if they do not already exist in the database.
 */
@Component
public class DataLoader implements CommandLineRunner {

    /**
     * Service for handling user-related operations.
     */
    @Autowired
    private UserService userService;

    /**
     * Runs at application startup to create default users.
     *
     * @param args Command-line arguments (not used in this context)
     * @throws Exception if an error occurs during user creation
     */
    @Override
    public void run(String... args) throws Exception {
        if (userService.findByEmail("admin@example.com") == null) {
            createAdmin();
        }

        if (userService.findByEmail("employee1@example.com") == null) {
            createEmployee("employee1@example.com", "Employee One");
        }
        if (userService.findByEmail("employee2@example.com") == null) {
            createEmployee("employee2@example.com", "Employee Two");
        }
        if (userService.findByEmail("employee3@example.com") == null) {
            createEmployee("employee3@example.com", "Employee Three");
        }
    }

    /**
     * Creates a default admin account if it does not exist.
     */
    private void createAdmin() {
        UserRegistrationDto adminDto = new UserRegistrationDto();
        adminDto.setFirstName("Admin");
        adminDto.setLastName("Admin");
        adminDto.setEmail("admin@example.com");
        adminDto.setPassword("admin123");
        userService.save(adminDto); // Assigned ROLE_ADMIN in UserServiceImpl
    }

    /**
     * Creates a default employee account with the given email and name if it does not exist.
     *
     * @param email Email address of the employee
     * @param name  Name of the employee
     */
    private void createEmployee(String email, String name) {
        UserRegistrationDto employeeDto = new UserRegistrationDto();
        employeeDto.setFirstName(name);
        employeeDto.setLastName(name);
        employeeDto.setEmail(email);
        employeeDto.setPassword("employee123");
        userService.save(employeeDto); // Assigned ROLE_EMPLOYEE in UserServiceImpl
    }
}
