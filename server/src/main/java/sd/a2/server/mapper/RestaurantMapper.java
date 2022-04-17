package sd.a2.server.mapper;

import sd.a2.server.dto.NewRestaurantDto;
import sd.a2.server.dto.RestaurantDto;
import sd.a2.server.model.Restaurant;

public class RestaurantMapper {
    public static RestaurantDto toDto(Restaurant restaurant){
        var r = new RestaurantDto();
        if(restaurant.getFoods()!= null)
            r.setFoods(restaurant.getFoods().stream().map(FoodMapper::toDto).toList());
        r.setId(restaurant.getId());
        r.setName(restaurant.getName());
        r.setLocation(restaurant.getLocation());
        return r;
    }

    public static Restaurant toEntity(NewRestaurantDto newRestaurantDto){
        var r = new Restaurant();
        r.setName(newRestaurantDto.getName());
        r.setLocation(newRestaurantDto.getLocation());
        return r;
    }
}
