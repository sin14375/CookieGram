package cookiegram.ca.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import cookiegram.ca.application.model.Role;
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.repository.RoleRepository;
import cookiegram.ca.application.repository.UserRepository;
import cookiegram.ca.application.service.UserServiceImpl;
import cookiegram.ca.application.web.dto.UserRegistrationDto;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        
        Field roleRepositoryField = UserServiceImpl.class.getDeclaredField("roleRepository");
        roleRepositoryField.setAccessible(true);
        roleRepositoryField.set(userService, roleRepository);

        Field passwordEncoderField = UserServiceImpl.class.getDeclaredField("passwordEncoder");
        passwordEncoderField.setAccessible(true);
        passwordEncoderField.set(userService, passwordEncoder);
    }

    @Test
    void testSave_Good() {
        UserRegistrationDto registrationDto = new UserRegistrationDto("John", "Doe", "user@example.com", "password123");
        Role role = new Role("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User("John", "Doe", "user@example.com", "encodedPassword", List.of(role)));

        User result = userService.save(registrationDto);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("user@example.com", result.getEmail());
        assertEquals(1, result.getRoles().size());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testSave_Bad_InvalidUserData() {
        UserRegistrationDto registrationDto = new UserRegistrationDto("John", "Doe", "user@example.com", "password123");
        Role role = new Role("ROLE_USER");
        when(roleRepository.findByName("ROLE_USER")).thenReturn(role);
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("User already exists"));

        try {
            userService.save(registrationDto);
            fail("Expected an exception due to existing user");
        } catch (RuntimeException e) {
            assertEquals("User already exists", e.getMessage());
        }
    }
}
