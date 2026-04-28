package uptc.edu.swii.customer.service;

import java.util.List;

import uptc.edu.swii.customer.dto.CustomerRequest;
import uptc.edu.swii.customer.event.CustomerEvent;
import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class CustomerEventConsumer {

    @Autowired
    private CustomerService customerService;

    private JsonUtils jsonUtils = new JsonUtils();

    @KafkaListener(topics = "customer_events", groupId = "customer_group")
    public void handleCustomerEvents(String eventJson){

        CustomerEvent event = jsonUtils.fromJson(eventJson, CustomerEvent.class);

        switch (event.getEventType()) {

            case "ADD_CUSTOMER":
                CustomerRequest addCustomer =
                        jsonUtils.fromJson(event.getData(), CustomerRequest.class);
                customerService.save(addCustomer);
                System.out.println("Cliente agregado");
                break;

            case "EDIT_CUSTOMER":
                CustomerRequest editCustomer =
                        jsonUtils.fromJson(event.getData(), CustomerRequest.class);
                customerService.save(editCustomer);
                System.out.println("Cliente editado");
                break;

            case "FIND_BY_ID":
                String id = event.getData();
                Customer customer = customerService.findById(id);
                System.out.println("Cliente encontrado: " + jsonUtils.toJson(customer));
                break;

            case "FIND_ALL":
                System.out.println("Lista clientes: "
                        + jsonUtils.toJson(customerService.findAll()));
                break;

            default:
                System.out.println("Evento no soportado");
        }
    }
}