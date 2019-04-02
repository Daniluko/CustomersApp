package springboot.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.dao.CustomerDao;
import springboot.entity.Customer;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Danylo on 01.04.2019
 */
@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private static final Logger LOG = LogManager.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CustomerDao customersDao;

    @Override
    public Set<Customer> getAllCustomers() {
        LOG.debug("get all customers");
        HashSet<Customer> result = new HashSet<>(customersDao.getAllCustomers());
        LOG.debug("get all customers return {} result", result.size());
        return result;
    }

    @Override
    public Customer findCustomerById(BigDecimal id) {
        LOG.debug("find customer by id, id={}", id);
        Customer result = customersDao.findCustomerById(id);
        LOG.debug("find customer by id, result={}", result);
        return result;
    }

    @Override
    public void insertCustomer(Customer customer) {
        LOG.debug("insert customer, customer={}", customer);
        customersDao.insertCustomer(customer);
        LOG.debug("successfully");
    }

    @Override
    public void updateCustomer(Customer customer) {
        LOG.debug("update customer, customer={}", customer);
        customersDao.updateCustomer(customer);
        LOG.debug("successfully");
    }

    @Override
    public void deleteCustomer(BigDecimal id) {
        LOG.debug("delete customer, id={}", id);
        customersDao.deleteCustomer(id);
        LOG.debug("successfully");
    }
}
