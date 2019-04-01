package springboot.service;

import springboot.entity.Customer;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Danylo on 01.04.2019
 */

public interface CustomerService {

    Set<Customer> getAllCustomers();

    Customer findCustomerById(BigDecimal id);

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(BigDecimal id);
}
