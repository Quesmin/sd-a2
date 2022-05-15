package sd.a2.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AdminDto extends UserDto {
    private final Boolean isAdmin = true;
    List<AdminRestaurantDto> restaurants;
}
