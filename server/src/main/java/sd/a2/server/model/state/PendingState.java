package sd.a2.server.model.state;

import sd.a2.server.model.Order;
import sd.a2.server.model.OrderStatus;

public class PendingState extends State{
    public PendingState(Order order) {
        super(order);
    }

    @Override
    public void advanceOrder() {
        this.order.setOrderStatus(OrderStatus.ACCEPTED);
    }

    @Override
    public void declineOrder() {
        this.order.setOrderStatus(OrderStatus.DECLINED);
    }
}
