package uptc.edu.swii.order.service;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.utils.JsonUtils;

@Service
public class OrderEventConsumer {

    private final OrderService orderService;
    private final JsonUtils jsonUtils;

    public OrderEventConsumer(OrderService orderService,
                              JsonUtils jsonUtils){
        this.orderService = orderService;
        this.jsonUtils = jsonUtils;
    }

    @KafkaListener(topics = "addorder_events", groupId = "order_group")
    public void handleAddOrderEvent(String order){
        Order newOrder = jsonUtils.fromJson(order, Order.class);
        newOrder.setId(null);
        orderService.save(newOrder);
    }
}