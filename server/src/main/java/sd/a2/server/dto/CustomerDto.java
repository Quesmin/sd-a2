package sd.a2.server.dto;

import lombok.Getter;
import lombok.Setter;
import sd.a2.server.model.Customer;

import java.util.List;

@Setter
@Getter
public class CustomerDto extends UserDto {
    private List<OrderDto> orders;
    private final Boolean isAdmin = false;


    public CustomerDto(){
        super();
    }
    public CustomerDto(String id, String email, List<OrderDto> orders){
        super(id, email);
        this.orders = orders;
    }
}
