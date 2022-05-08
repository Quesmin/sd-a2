package sd.a2.server.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.repository.UserRepository;
import sd.a2.server.dto.LoginUserDto;
import sd.a2.server.dto.UserDto;
import sd.a2.server.mapper.UserMapper;

@Service
public class UserService {
    private final UserRepository userRepository;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto login(LoginUserDto customerDto) throws Exception {
        var user = userRepository.findUserByEmail(customerDto.getEmail());
        if(user.isEmpty()){
            logger.error("Customer doesn't exist!");
            throw new Exception("Customer doesn't exist!");
        }

        if(!BCrypt.checkpw(customerDto.getPassword(), user.get().getPasswordHash())){
            logger.error("Incorrect password!");
            throw new Exception("Incorrect password!");
        }
        logger.info("User just logged in.");
        return UserMapper.toDto(user.get());
    }
}
