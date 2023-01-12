package pl.tacocloud.tacos.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.tacocloud.tacos.Order;
import pl.tacocloud.tacos.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrdersByOrderedByOrderByPlacedAtDesc(User user, PageRequest request);
}
