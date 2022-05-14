package sd.a2.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {
    @OneToMany
    List<Order> orders;

    public Customer(String id, String email, String passwordHash, List<Order> orders){
        this.setEmail(email);
        this.setPasswordHash(passwordHash);
        this.setOrders(orders);
        this.setId(id);
    }

    public void addOrder(Order o){
        orders.add(o);
    }
}
