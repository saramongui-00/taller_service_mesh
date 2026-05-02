package uptc.edu.swii.order.service;


import org.springframework.stereotype.Service;
import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public Order update(Order order){
        Optional<Order>existingOrder = orderRepository.findById(order.getId());

        if(existingOrder.isPresent()){
            Order orderToUpdate = existingOrder.get();

            if(order.getId() != null){
                orderToUpdate.setId(order.getId());
            }if(order.getCustomerid() != null){
                orderToUpdate.setCustomerid(order.getCustomerid());
            }if (order.getProduct() != null){
                orderToUpdate.setProduct(order.getProduct());
            }
            return orderToUpdate;
        }

        throw new RuntimeException("Orden no encontrada ");
    }

    public boolean deleteById(Long id){
        if(orderRepository.existsById(id)){
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public List<Order> findByCustomerId(String customerid){
        return orderRepository.findByCustomerid(customerid);
    }
}