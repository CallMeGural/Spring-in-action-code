package pl.tacocloud.tacos.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.tacocloud.tacos.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Order save(Order order);
}
