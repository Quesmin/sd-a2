package sd.a2.server.model.state;

import sd.a2.server.model.Order;

public class DeliveredState extends State{
    public DeliveredState(Order order) {
        super(order);
    }

    @Override
    public void advanceOrder() {

    }

    @Override
    public void declineOrder() {

    }
}
