package pl.tacocloud.tacos.web;

import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.tacocloud.tacos.Order;
import pl.tacocloud.tacos.User;
import pl.tacocloud.tacos.data.OrderRepository;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(@ModelAttribute Order order, @AuthenticationPrincipal User user) {
        //model.addAttribute("order",new Order());
        System.out.println(user);
        if(order.getName()==null) order.setName(user.getFullname());
        if(order.getStreet()==null) order.setStreet(user.getStreet());
        if(order.getCity()==null) order.setCity(user.getCity());
        if(order.getState()==null) order.setState(user.getState());
        if(order.getZip()==null) order.setZip(user.getZip());
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        PageRequest pageable = PageRequest.of(0,20);
        model.addAttribute("orders",orderRepository.findOrdersByOrderedByOrderByPlacedAtDesc(user,pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors,
                               SessionStatus status, @AuthenticationPrincipal User user) {
        if(errors.hasErrors()) return "orderForm";
        order.setOrderedBy(user);
        orderRepository.save(order);
        status.setComplete(); //wyczyszczenie sesji - aby mozna bylo utworzyc nowe, puste zamowienie
        return "redirect:/";
    }
}
