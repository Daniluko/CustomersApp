package springboot.dao;

import springboot.entity.Customer;

import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Danylo on 31.03.2019
 */
public interface CustomerDao{

    Set<Customer> getAllCustomers();

    Customer findCustomerById(BigDecimal id);

    void insertCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(BigDecimal id);
}
