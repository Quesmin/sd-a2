package sd.a2.server.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.repository.AdminRepository;
import sd.a2.server.repository.RestaurantRepository;
import sd.a2.server.dto.NewRestaurantDto;
import sd.a2.server.dto.RestaurantDto;
import sd.a2.server.mapper.RestaurantMapper;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final AdminRepository adminRepository;
    private Logger logger = LoggerFactory.getLogger(RestaurantService.class);

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, AdminRepository adminRepository) {
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
    }

    /**
     * Retrieves the restaurant with the given id.
     * @param id
     * @return
     * @throws Exception
     */
    public RestaurantDto getById(String id) throws Exception {
        var r = restaurantRepository.findById(id);
        if (r.isEmpty()){
            logger.error("Restaurant doesn't exist!");
            throw new Exception("Restaurant doesn't exist");
        }
        logger.info("Find restaurant by id.");
        return RestaurantMapper.toDto(r.get());
    }

    public List<RestaurantDto> getAll() {
        logger.info("Get all restaurants.");
        return restaurantRepository.findAll().stream().map(RestaurantMapper::toDto).toList();
    }

    /**
     * Creates a new restaurant in the current database.
     * @param newRestaurantDto
     * @return
     * @throws Exception
     */
    @Transactional
    public RestaurantDto add(NewRestaurantDto newRestaurantDto) throws Exception {
        var admin = adminRepository.findById(newRestaurantDto.getAdminId());

        if(admin.isEmpty()){
            logger.error("Admin doesn't exist!");
            throw new Exception("Admin doesn't exist!");
        }

        var entity = RestaurantMapper.toEntity(newRestaurantDto);
        entity.setAdmin(admin.get());
        var response = restaurantRepository.save(entity);
        admin.get().addRestaurant(response);
        logger.info("New restaurant was added.");
        return RestaurantMapper.toDto(response);
    }
}
