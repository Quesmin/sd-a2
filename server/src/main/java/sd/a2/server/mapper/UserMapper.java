package sd.a2.server.mapper;

import sd.a2.server.dto.UserDto;
import sd.a2.server.model.Admin;
import sd.a2.server.model.Customer;
import sd.a2.server.model.User;

public class UserMapper {
    public static UserDto toDto(User user){
        if(user instanceof Customer) return CustomerMapper.toDto((Customer) user);
        return AdminMapper.toDto((Admin) user);
    }
}
