package sd.a2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewCustomerDto {
    private String email;
    private String password;
}
