package sd.a2.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
@NoArgsConstructor
public class Admin extends User{

    @OneToMany
    private List<Restaurant> restaurants;

    public Admin(String id, List<Restaurant> restaurants){
        super(id);
        this.restaurants = restaurants;
    }

    public void addRestaurant(Restaurant r){
        restaurants.add(r);
    }

}
