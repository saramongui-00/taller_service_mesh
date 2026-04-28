package uptc.edu.swii.customer.service;
import uptc.edu.swii.customer.dto.CustomerRequest;
import uptc.edu.swii.customer.event.CustomerEvent;
import uptc.edu.swii.customer.event.CustomerEventType;
import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class CustomerEventProducer {

    private static final String TOPIC = "customer_events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private JsonUtils jsonUtils = new JsonUtils();

    private void sendEvent(CustomerEventType type, String dataJson){
        CustomerEvent event = new CustomerEvent(type.name(), dataJson);
        String eventJson = jsonUtils.toJson(event);
        kafkaTemplate.send(TOPIC, eventJson);
    }

    public void sendAddCustomerEvent(CustomerRequest customer){
        sendEvent(CustomerEventType.ADD_CUSTOMER, jsonUtils.toJson(customer));
    }

    public void sendEditCustomerEvent(Customer customer){
        sendEvent(CustomerEventType.EDIT_CUSTOMER, jsonUtils.toJson(customer));
    }

    public void sendFindByCustomerIDEvent(String document){
        sendEvent(CustomerEventType.FIND_BY_ID, document);
    }

    public void sendFindAllCustomersEvent(){
        sendEvent(CustomerEventType.FIND_ALL, "");
    }
}