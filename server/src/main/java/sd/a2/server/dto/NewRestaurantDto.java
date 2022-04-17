package sd.a2.server.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewRestaurantDto {
    private String name;
    private String location;
    private String adminId;
}
