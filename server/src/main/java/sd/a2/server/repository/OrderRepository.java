package sd.a2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.a2.server.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}
