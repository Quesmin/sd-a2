package sd.a2.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.model.Food;
import sd.a2.server.repository.FoodRepository;
import sd.a2.server.repository.RestaurantRepository;
import sd.a2.server.dto.FoodDto;
import sd.a2.server.dto.NewFoodDto;
import sd.a2.server.mapper.FoodMapper;

import javax.transaction.Transactional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private Logger logger = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    public FoodService(FoodRepository foodRepository, RestaurantRepository restaurantRepository) {
        this.foodRepository = foodRepository;
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Adds a food item to the given restaurant.
     * @param newFood
     * @return
     * @throws Exception
     */
    @Transactional
    public FoodDto addFoodToRestaurant(NewFoodDto newFood) throws Exception {
        var restaurant = restaurantRepository.findById(newFood.getRestaurantId());

        if(restaurant.isEmpty()){
            logger.error("Restaurant doesn't exist!");
            throw new Exception("Restaurant doesn't exist!");
        }

        if(newFood.getDescription().isEmpty() || newFood.getName().isEmpty()){
            logger.error("Invalid data!");
            throw new Exception("Invalid data!");
        }
        if(newFood.getPrice() <= 0){
            logger.error("Invalid price!");
            throw new Exception("Invalid price!");
        }

        var entity = FoodMapper.toEntity(newFood);
        entity.setRestaurant(restaurant.get());
        var response = foodRepository.save(entity);
        restaurant.get().addFood(response);
        logger.info("New food was added.");
        return FoodMapper.toDto(response);
    }
}
