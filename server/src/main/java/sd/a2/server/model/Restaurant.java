package sd.a2.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.List;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(
            name = "id"
    )
    private String id;

    private String name;
    private String location;

    @ManyToOne
    private Admin admin;

    @OneToMany
    private List<Order> orders;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Food> foods;

    public void addOrder(Order o){
        orders.add(o);
    }


    public void addFood(Food food){
        foods.add(food);
    }
}
