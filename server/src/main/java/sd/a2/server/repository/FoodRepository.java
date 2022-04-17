package sd.a2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sd.a2.server.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, String> {
}
