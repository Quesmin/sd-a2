package sd.a2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewRestaurantDto {
    private String name;
    private String location;
    private String adminId;
}
