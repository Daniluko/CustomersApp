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

/**
 * Created by Danylo on 31.03.2019
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger LOG = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public @ResponseBody
    Customer getCustomerById (@PathVariable("id") BigDecimal id){
        LOG.info("getCustomerById started, id={}", id);
        Customer result = customerService.findCustomerById(id);
        LOG.info("getCustomerById done");
        return result;
    }

    @PostMapping
    public void insertCustomer(@Valid @RequestBody Customer customerRequest) {
        LOG.info("Insert customer, customerRequest={}", customerRequest);
        customerService.insertCustomer(customerRequest);
        LOG.info("successfully");
    }

    @PutMapping("/{id}")
    public void updateCustomerById(@PathVariable("id") BigDecimal id, @RequestParam("cust_num") BigDecimal cust_num){
        LOG.info("Update customer, id={}, cust_num={}", id, cust_num);
        Customer customer = customerService.findCustomerById(id);
        if(Objects.isNull(customer)){
            LOG.warn("not existing customer");
        } else {
            customer.setCustNum(cust_num);
            customerService.updateCustomer(customer);
        }
        LOG.info("successfully");
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerById(@PathVariable("id") BigDecimal id){
        LOG.info("Delete customer By Id, id={}", id);
        customerService.deleteCustomer(id);
        LOG.info("successfully");
    }

}
