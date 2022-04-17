package sd.a2.server.mapper;

import sd.a2.server.dto.OrderDto;
import sd.a2.server.model.Order;

public class OrderMapper {

    public static OrderDto toDto(Order order){
        var newOrder = new OrderDto();
        newOrder.setId(order.getId());
        newOrder.setOrderStatus(order.getOrderStatus());
        newOrder.setOrderedFoods(order.getOrderedFoods()
                .stream().map(OrderedFoodMapper::toDto).toList());
        return newOrder;
    }
}
