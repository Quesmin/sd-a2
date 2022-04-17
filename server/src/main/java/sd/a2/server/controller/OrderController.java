package sd.a2.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.a2.server.dto.NewOrderDto;
import sd.a2.server.dto.OrderDto;
import sd.a2.server.model.OrderStatus;
import sd.a2.server.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @PostMapping
    public ResponseEntity<OrderDto> placeOrder(@RequestBody NewOrderDto order){
        try{
            var dto = orderService.addOrder(order);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "{orderId}")
    public ResponseEntity<OrderDto> setOrderStatus(@PathVariable("orderId")String id, @RequestBody OrderStatus orderStatus){
        try{
            var dto = orderService.changeOrderStatus(id, orderStatus);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
