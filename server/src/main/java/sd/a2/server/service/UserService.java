package sd.a2.server.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.repository.UserRepository;
import sd.a2.server.dto.LoginUserDto;
import sd.a2.server.dto.UserDto;
import sd.a2.server.mapper.UserMapper;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto login(LoginUserDto customerDto) throws Exception {
        var user = userRepository.findUserByEmail(customerDto.getEmail());
        if(user.isEmpty()){
            throw new Exception("Customer doesn't exist!");
        }

        if(!BCrypt.checkpw(customerDto.getPassword(), user.get().getPasswordHash())){
            throw new Exception("Incorrect password!");
        }
        return UserMapper.toDto(user.get());
    }
}
