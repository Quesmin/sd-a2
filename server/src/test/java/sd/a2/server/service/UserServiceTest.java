package sd.a2.server.service;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sd.a2.server.dto.LoginUserDto;
import sd.a2.server.model.Customer;
import sd.a2.server.model.User;
import sd.a2.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserServiceTest(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Test
    public void loginTest() {
        String userId = UUID.randomUUID().toString();
        LoginUserDto loginUserDto = new LoginUserDto("test@email.com", "123");

        Customer customer = new Customer(userId, "test@email.com", BCrypt.hashpw("123", BCrypt.gensalt()), new ArrayList<>());

        Mockito.when(userRepository.findUserByEmail(Mockito.anyString())).thenReturn(Optional.of(customer));

        assertDoesNotThrow(() -> userService.login(loginUserDto));
    }
}
