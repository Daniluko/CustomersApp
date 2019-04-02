package springboot.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.entity.Customer;
import springboot.service.CustomerService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Danylo on 31.03.2019
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger LOG = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public @ResponseBody
    Set<Customer> getAllCustomers (){
        LOG.info("get all customers");
        Set <Customer> result = customerService.getAllCustomers();
        LOG.info("successfully");
        return result;
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Customer getCustomerById (@PathVariable("id") BigDecimal id){
        LOG.info("get customer by id , id={}", id);
        Customer result = customerService.findCustomerById(id);
        LOG.info("successfully");
        return result;
    }

    @PostMapping
    public void insertCustomer(@Valid @RequestBody Customer customerRequest) {
        LOG.info("Insert customer, customerRequest={}", customerRequest);
        customerService.insertCustomer(customerRequest);
        LOG.info("successfully");
    }

    @PutMapping("/{id}")
    public void updateCustomer(@PathVariable("id") BigDecimal id, @RequestParam("credit_limit") BigDecimal credit_limit){
        LOG.info("Update customer");
        Customer customer = customerService.findCustomerById(id);
        if(Objects.isNull(customer)){
            LOG.warn("not found customer with your id");
        } else {
            customer.setCreditLimit(credit_limit);
            customerService.updateCustomer(customer);
        }
        LOG.info("successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable("id") BigDecimal id){
        LOG.info("Delete customer By Id, id={}", id);
        customerService.deleteCustomer(id);
        LOG.info("successfully");
    }

}
