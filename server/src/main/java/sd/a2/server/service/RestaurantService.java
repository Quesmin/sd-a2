package sd.a2.server.service;

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

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, AdminRepository adminRepository) {
        this.restaurantRepository = restaurantRepository;
        this.adminRepository = adminRepository;
    }

    public RestaurantDto getById(String id) throws Exception {
        var r = restaurantRepository.findById(id);
        if (r.isEmpty()){
            throw new Exception("Restaurant doesn't exist");
        }
        return RestaurantMapper.toDto(r.get());
    }

    public List<RestaurantDto> getAll() {
        return restaurantRepository.findAll().stream().map(RestaurantMapper::toDto).toList();
    }

    @Transactional
    public RestaurantDto add(NewRestaurantDto newRestaurantDto) throws Exception {
        var admin = adminRepository.findById(newRestaurantDto.getAdminId());

        if(admin.isEmpty()){
            throw new Exception("Admin doesn't exist!");
        }

        var entity = RestaurantMapper.toEntity(newRestaurantDto);
        entity.setAdmin(admin.get());
        var response = restaurantRepository.save(entity);
        admin.get().addRestaurant(response);
        return RestaurantMapper.toDto(response);
    }
}
