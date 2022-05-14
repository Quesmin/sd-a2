package sd.a2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewOrderedFoodDto {
    private String foodId;
    private Integer quantity;
}
