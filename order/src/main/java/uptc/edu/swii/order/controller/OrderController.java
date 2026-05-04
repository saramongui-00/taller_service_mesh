package uptc.edu.swii.order.controller;

import org.springframework.web.bind.annotation.*;
import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.service.OrderService;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping("/addorder")
    public void createOrder(@RequestBody Order order) {
        service.save(order);
    }

    @PostMapping("/editorder")
    public void updateOrder(@RequestBody Order order) {
        service.update(order);
    }

    @GetMapping("/getorders")
    public List<Order> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/deleteorder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return "Orden eliminado correctamente";
        }
        return "Orden no encontrada o no se puede eliminar";
    }

    @GetMapping("order/{customerid}")
    public List<Order> getByCustomer(@PathVariable String customerid) {
        return service.findByCustomerId(customerid);
    }
}