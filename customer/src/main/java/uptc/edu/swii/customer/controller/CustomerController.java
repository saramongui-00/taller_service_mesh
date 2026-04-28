package uptc.edu.swii.customer.controller;

import uptc.edu.swii.customer.dto.CustomerRequest;
import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.service.CustomerEventProducer;
import uptc.edu.swii.customer.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerEventProducer customerEventProducer;
    private static JsonUtils jsonUtils= new JsonUtils();

    @PostMapping("/addcustomer")
    public String sendMessageAddCustomer(@RequestBody String customer) {
        CustomerRequest customerObj = jsonUtils.fromJson(customer, CustomerRequest.class);
        customerEventProducer.sendAddCustomerEvent(customerObj);
        return customerEventProducer.toString();
    }

    @PostMapping("/editcustomer")
    public String sendMessageEditCustomer(@RequestBody String customer) {
        Customer customerObj = new Customer();
        customerObj = jsonUtils.fromJson(customer, Customer.class);
        customerEventProducer.sendEditCustomerEvent(customerObj);
        return customerEventProducer.toString();
    }
}

