package sd.a2.server.mapper;

import sd.a2.server.dto.AdminRestaurantDto;
import sd.a2.server.model.Restaurant;

public class AdminRestaurantMapper {
    public static AdminRestaurantDto toDto(Restaurant restaurant) {
        var newRest = new AdminRestaurantDto();
        if (restaurant.getFoods() != null)
            newRest.setFoods(restaurant.getFoods().stream().map(FoodMapper::toDto).toList());
        newRest.setId(restaurant.getId());
        newRest.setName(restaurant.getName());
        newRest.setLocation(restaurant.getLocation());
        if (restaurant.getOrders() != null)
            newRest.setOrders(restaurant.getOrders().stream().map(OrderMapper::toDto).toList());
        return newRest;
    }
}
