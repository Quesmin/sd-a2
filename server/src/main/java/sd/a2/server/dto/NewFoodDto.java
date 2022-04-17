package sd.a2.server.dto;

import lombok.Getter;
import sd.a2.server.model.state.FoodCategory;

@Getter
public class NewFoodDto {
    private String name;
    private String description;
    private Double price;
    private String restaurantId;
    private FoodCategory category;
}
