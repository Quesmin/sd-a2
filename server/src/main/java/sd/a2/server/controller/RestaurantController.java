package sd.a2.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.a2.server.dto.NewRestaurantDto;
import sd.a2.server.dto.RestaurantDto;
import sd.a2.server.service.RestaurantService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.restaurantService = service;
    }



    @GetMapping(path = "{id}")
    public ResponseEntity<RestaurantDto> get(@PathVariable("id") String id) {
        try{
            var dto = restaurantService.getById(id);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAll() {
        return new ResponseEntity<>(restaurantService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RestaurantDto> add(@RequestBody NewRestaurantDto newRestaurantDto){
        try {
            var res = restaurantService.add(newRestaurantDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
