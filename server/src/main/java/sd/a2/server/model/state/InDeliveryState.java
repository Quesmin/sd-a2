package sd.a2.server.model.state;

import sd.a2.server.model.Order;
import sd.a2.server.model.OrderStatus;

public class InDeliveryState extends State{
    public InDeliveryState(Order order) {
        super(order);
    }

    @Override
    public void advanceOrder() {
        this.order.setOrderStatus(OrderStatus.DELIVERED);
    }

    @Override
    public void declineOrder() {

    }
}
