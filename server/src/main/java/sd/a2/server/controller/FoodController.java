package sd.a2.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.a2.server.dto.FoodDto;
import sd.a2.server.dto.NewFoodDto;
import sd.a2.server.service.FoodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/food")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService service) {
        this.foodService = service;
    }

    @PostMapping
    public ResponseEntity<FoodDto> addFoodToRestaurant(@RequestBody NewFoodDto newFood){
        try{
            var dto = foodService.addFoodToRestaurant(newFood);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
