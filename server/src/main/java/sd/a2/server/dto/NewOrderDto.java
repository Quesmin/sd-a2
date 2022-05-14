package sd.a2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NewOrderDto {
    private String customerId;
    private List<NewOrderedFoodDto> orderedFoods;
    private String restaurantId;
}
