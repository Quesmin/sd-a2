package sd.a2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedFoodDto {
    private String id;
    private FoodDto food;
    private Integer quantity;
}
