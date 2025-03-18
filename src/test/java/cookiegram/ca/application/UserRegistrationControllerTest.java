package cookiegram.ca.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;  
import cookiegram.ca.application.model.User;
import cookiegram.ca.application.service.UserService;
import cookiegram.ca.application.web.UserRegistrationController;
import cookiegram.ca.application.web.dto.UserRegistrationDto;

@ExtendWith({MockitoExtension.class})
class UserRegistrationControllerTest 
{
	 @Mock
    private UserService userService;

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    @Test
    void testRegisterUserAccount_Good() {
        UserRegistrationDto validUser = new UserRegistrationDto("John", "Doe", "john.doe@example.com", "StrongPass@123");
        when(userService.save(any(UserRegistrationDto.class))).thenReturn(new User());
        String redirectView = userRegistrationController.registerUserAccount(validUser);
        Assertions.assertEquals("redirect:/registration?success", redirectView);
        verify(userService, times(1)).save(any(UserRegistrationDto.class));
    }

    @Test
    void testRegisterUserAccount_Bad() {
        UserRegistrationDto invalidUser = new UserRegistrationDto("", "", "invalid-email", "");
        doThrow(new IllegalArgumentException("Invalid user data")).when(userService).save(any(UserRegistrationDto.class));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userRegistrationController.registerUserAccount(invalidUser);
        });
        verify(userService, times(1)).save(any(UserRegistrationDto.class));
    }

    @Test
    void testRegisterUserAccount_Boundary() {
        UserRegistrationDto boundaryUser = new UserRegistrationDto(
            "A".repeat(255),
            "B".repeat(255),
            "longemail" + "a".repeat(240) + "@example.com",
            "P@ssword123"
        );
        when(userService.save(any(UserRegistrationDto.class))).thenReturn(new User());
        String redirectViewBoundary = userRegistrationController.registerUserAccount(boundaryUser);
        Assertions.assertEquals("redirect:/registration?success", redirectViewBoundary);
        verify(userService, times(1)).save(any(UserRegistrationDto.class));
    }
}
