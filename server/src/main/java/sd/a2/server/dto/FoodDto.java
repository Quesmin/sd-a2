package sd.a2.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String category;
}
