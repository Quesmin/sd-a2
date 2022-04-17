package sd.a2.server.dto;

import lombok.Getter;
import lombok.Setter;
import sd.a2.server.model.OrderStatus;

import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String id;
    private OrderStatus orderStatus;
    private List<OrderedFoodDto> orderedFoods;
}
