package uptc.edu.swii.order.service;


import org.springframework.stereotype.Service;
import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByCustomerId(String customerid){
        return orderRepository.findByCustomerid(customerid);
    }
}