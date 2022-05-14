package sd.a2.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FoodDto {
    private String id;
    private String name;
    private String description;
    private Double price;
    private String category;
}
