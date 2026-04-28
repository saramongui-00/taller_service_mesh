package uptc.edu.swii.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uptc.edu.swii.customer.dto.CustomerRequest;
import uptc.edu.swii.customer.model.Customer;
import uptc.edu.swii.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean save(CustomerRequest customerRequest){
        boolean flag = false;
        Customer mapCustomer = new Customer(customerRequest.getDocument(), customerRequest.getFirstname(),
                customerRequest.getLastname(), customerRequest.getAddress(), customerRequest.getPhone(), customerRequest.getEmail());
        Customer processCustomer = customerRepository.saveAndFlush(mapCustomer);
        if (processCustomer!=null){

            flag=true;
        }
        return flag;
    }

    public boolean delete(Customer customer){
        boolean flag = false;
        try{
            customerRepository.delete(customer);
            flag=true;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public Customer findById(String document){
        Customer customer=null;
        Optional<Customer> optionalCustomer = customerRepository.findById(document);
        if (optionalCustomer.isPresent())
            customer = optionalCustomer.get();
        return customer;
    }

    public List<Customer> findAll(){
        List<Customer> listCustomer = new ArrayList<Customer>();
        Iterable<Customer> customers = customerRepository.findAll();
        customers.forEach((o) ->{
            listCustomer.add(o);
        });
        return listCustomer;
    }

}
