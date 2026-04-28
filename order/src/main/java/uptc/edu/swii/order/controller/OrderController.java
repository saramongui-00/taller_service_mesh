package uptc.edu.swii.order.controller;


import org.springframework.web.bind.annotation.*;
import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.service.OrderEventProducer;
import uptc.edu.swii.order.service.OrderService;

import java.util.List;

@RestController
public class OrderController {

    private final OrderEventProducer producer;
    private final OrderService service;

    public OrderController(OrderEventProducer producer,
                           OrderService service){
        this.producer = producer;
        this.service = service;
    }

    @PostMapping("/addorder")
    public void createOrder(@RequestBody Order order){
        producer.sendAddOrderEvent(order);
    }

    @GetMapping("/orders")
    public List<Order> getAll(){
        return service.findAll();
    }

    @GetMapping("order/{customerid}")
    public List<Order> getByCustomer(@PathVariable String customerid){
        return service.findByCustomerId(customerid);
    }
}