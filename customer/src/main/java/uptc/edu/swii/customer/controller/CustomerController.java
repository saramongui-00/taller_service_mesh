package uptc.edu.swii.customer.controller;

import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.service.CustomerService;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping("/addcustomer")
    public void addCustomer(@RequestBody Customer customer) {
        service.save(customer);
    }

    @PostMapping("/editcustomer")
    public void updateCustomer(@RequestBody Customer customer) {
        service.update(customer);
    }

    @GetMapping("/getCustomers")
    public List<Customer> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/deletecustomer/{id}")
    public String deleteCustomer(@PathVariable String id) {
        if (service.deleteById(id)) {
            return "Cliente eliminado correctamente";
        }
        return "Cliente no encontrado o no se puede eliminar";
    }

}
