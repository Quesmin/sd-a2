package sd.a2.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.repository.CustomerRepository;
import sd.a2.server.repository.FoodRepository;
import sd.a2.server.repository.OrderRepository;
import sd.a2.server.repository.RestaurantRepository;
import sd.a2.server.dto.NewOrderDto;
import sd.a2.server.dto.OrderDto;
import sd.a2.server.mapper.OrderMapper;
import sd.a2.server.model.Order;
import sd.a2.server.model.OrderStatus;
import sd.a2.server.model.OrderedFood;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final CustomerRepository customerRepository;
    private Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository, FoodRepository foodRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public OrderDto addOrder(NewOrderDto newOrder) throws Exception {
        var customer = customerRepository.findById(newOrder.getCustomerId());

        if (customer.isEmpty()){
            logger.error("Customer doesn't exist!");
            throw new Exception("Customer doesn't exist!");
        }

        if(newOrder.getOrderedFoods().isEmpty()){
            logger.error("Empty ordered items list!");
            throw new Exception("Empty ordered items list!");
        }

        var restaurant = restaurantRepository.findById(newOrder.getRestaurantId());

        if (restaurant.isEmpty()){
            logger.error("Restaurant doesn't exist!");
            throw new Exception("Restaurant doesn't exist!");
        }

        var order = new Order();
        order.setCustomer(customer.get());
        order.setRestaurant(restaurant.get());
        List<OrderedFood> foods = new ArrayList<>();

        for (var f : newOrder.getOrderedFoods()) {
            var food = foodRepository.findById(f.getFoodId());

            if (food.isEmpty()){
                logger.error("Invalid food id!");
                throw new Exception("Invalid food id!");
            }

            if (!Objects.equals(food.get().getRestaurant().getId(), restaurant.get().getId())){
                logger.error("Invalid food item!");
                throw new Exception("Invalid food item!");
            }

            foods.add(new OrderedFood(food.get(), f.getQuantity(), order));
        }

        customer.get().addOrder(order);
        restaurant.get().addOrder(order);
        order.setOrderedFoods(foods);

        var response = orderRepository.save(order);
        logger.info("New order was added.");
        return OrderMapper.toDto(response);
    }

    @Transactional
    public OrderDto changeOrderStatus(String id, OrderStatus orderStatus) throws Exception {
        var order = orderRepository.findById(id);
        if (order.isEmpty()){
            logger.error("Order doesn't exist!");
            throw new Exception("Order doesn't exist!");
        }

        if(orderStatus.getValue() - order.get().getOrderStatus().getValue() == 1){
            order.get().advanceStatus();
        }

        if(orderStatus.getValue() - order.get().getOrderStatus().getValue() == 4)
            order.get().declineOrder();

        logger.info("Order status was changed.");
        return OrderMapper.toDto(order.get());
    }
}
