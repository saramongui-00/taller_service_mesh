package uptc.edu.swii.order.service;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uptc.edu.swii.order.model.Order;
import uptc.edu.swii.order.utils.JsonUtils;

@Service
public class OrderEventProducer {

    private static final String TOPIC_ADD = "addorder_events";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonUtils jsonUtils;

    public OrderEventProducer(KafkaTemplate<String, String> kafkaTemplate,
                              JsonUtils jsonUtils){
        this.kafkaTemplate = kafkaTemplate;
        this.jsonUtils = jsonUtils;
    }

    public void sendAddOrderEvent(Order order){
        String json = jsonUtils.toJson(order);
        kafkaTemplate.send(TOPIC_ADD, json);
    }
}