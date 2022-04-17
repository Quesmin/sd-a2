package sd.a2.server.model.state;

import sd.a2.server.model.Order;

public abstract class State {
    protected Order order;

    public State(Order order) {
        this.order = order;
    }

    public abstract void advanceOrder();
    public abstract void declineOrder();
}
