package uptc.edu.swii.customer.service;

import java.util.List;
import java.util.Optional;

import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findById(customer.getDocument());

        if (existingCustomer.isPresent()) {
            Customer customerToUpdate = existingCustomer.get();

            if (customer.getDocument() != null) {
                customerToUpdate.setDocument(customer.getDocument());
            }
            if (customer.getFirstname() != null) {
                customerToUpdate.setFirstname(customer.getFirstname());
            }
            if (customer.getLastname() != null) {
                customerToUpdate.setLastname(customer.getLastname());
            }
            if (customer.getAddress() != null) {
                customerToUpdate.setAddress(customer.getAddress());
            }
            if (customer.getPhone() != null) {
                customerToUpdate.setPhone(customer.getPhone());
            }
            if (customer.getEmail() != null) {
                customerToUpdate.setEmail(customer.getEmail());
            }
            return customerToUpdate;
        }

        throw new RuntimeException("Cliente no encontrado ");
    }

    public boolean deleteById(String document) {
        if (customerRepository.existsById(document)) {
            customerRepository.deleteById(document);
            return true;
        }
        return false;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findByDocument(String document) {
        return customerRepository.findByDocument(document);
    }

}