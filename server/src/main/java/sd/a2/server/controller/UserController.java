package sd.a2.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.a2.server.dto.LoginUserDto;
import sd.a2.server.dto.UserDto;
import sd.a2.server.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService service) {
        this.userService = service;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserDto> loginCustomer(@RequestBody LoginUserDto customer) {
        try {
            UserDto dto = userService.login(customer);
            logger.info("Logging in a customer.");
            return new ResponseEntity<UserDto>(dto, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error logging a customer!");
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
