package pl.tacocloud.tacos.data;

import pl.tacocloud.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
