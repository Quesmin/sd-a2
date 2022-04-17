package sd.a2.server.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sd.a2.server.model.state.*;
import javax.persistence.*;
import java.util.List;

@Table
@Entity(name = "orders")
@Getter
@Setter
public class Order {
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
    @Transient
    private State state;
    private OrderStatus orderStatus;

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderedFood> orderedFoods;

    @ManyToOne
    private Restaurant restaurant;

    public Order() {
        orderStatus = OrderStatus.PENDING;
        state = new PendingState(this);
    }

    public void advanceStatus() {
        state = switch (this.orderStatus) {
            case PENDING -> new PendingState(this);
            case ACCEPTED -> new AcceptedState(this);
            case DECLINED -> new DeclinedState( this);
            case IN_DELIVERY -> new InDeliveryState(this);
            default -> new DeliveredState(this);
        };
        state.advanceOrder();
    }

    public void declineOrder() {
        state.declineOrder();
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
