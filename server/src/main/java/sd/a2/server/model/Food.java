package sd.a2.server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sd.a2.server.model.state.FoodCategory;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Food {

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
    private String description;
    private Double price;
    private FoodCategory category;


    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant Restaurant;

}
