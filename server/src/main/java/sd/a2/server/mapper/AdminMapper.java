package sd.a2.server.mapper;

import sd.a2.server.dto.AdminDto;
import sd.a2.server.model.Admin;

public class AdminMapper {
    public static AdminDto toDto(Admin admin){
        var newAdm = new AdminDto();
        newAdm.setId(admin.getId());
        newAdm.setEmail(admin.getEmail());
        newAdm.setRestaurants(admin.getRestaurants().stream().map(AdminRestaurantMapper::toDto).toList());
        return newAdm;
    }
}
