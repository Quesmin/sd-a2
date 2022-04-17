package sd.a2.server.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Table
@Entity
@Getter
public class Admin extends User{

    @OneToMany
    private List<Restaurant> restaurants;

    public void addRestaurant(Restaurant r){
        restaurants.add(r);
    }

}
