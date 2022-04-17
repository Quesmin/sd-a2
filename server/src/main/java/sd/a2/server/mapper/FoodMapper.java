package sd.a2.server.mapper;

import sd.a2.server.dto.FoodDto;
import sd.a2.server.dto.NewFoodDto;
import sd.a2.server.model.Food;

public class FoodMapper {

    public static FoodDto toDto(Food food){
        var newFood = new FoodDto();
        newFood.setDescription(food.getDescription());
        newFood.setName(food.getName());
        newFood.setId(food.getId());
        newFood.setPrice(food.getPrice());
        newFood.setCategory(food.getCategory().toString());
        return newFood;
    }

    public static Food toEntity(NewFoodDto newFood){
        var food = new Food();
        food.setDescription(newFood.getDescription());
        food.setName(newFood.getName());
        food.setPrice(newFood.getPrice());
        food.setCategory(newFood.getCategory());
        return food;
    }
}
