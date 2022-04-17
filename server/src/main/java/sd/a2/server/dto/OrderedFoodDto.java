package sd.a2.server.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderedFoodDto {
    private String id;
    private FoodDto food;
    private Integer quantity;
}
