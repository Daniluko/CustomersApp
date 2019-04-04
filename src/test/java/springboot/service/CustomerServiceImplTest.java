package springboot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import springboot.dao.CustomerDaoImpl;
import springboot.entity.Customer;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Danylo on 02.04.2019
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
    @Spy
    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl();

    @Mock
    private CustomerDaoImpl customerDao;

    private Customer customer1 = new Customer();
    private Customer customer2 = new Customer();

    @Test
    public void getAllCustomers() {
        Set<Customer> expected  = new HashSet<>(Arrays.asList(new Customer[]{customer1 , customer2}));
        doReturn(expected).when(customerDao).getAllCustomers();
        Set<Customer> actual = customerService.getAllCustomers();
        assertTrue(expected.containsAll(actual)&& actual.containsAll(expected));
    }

    @Test
    public void findCustomerById() {
       doReturn(customer1).when(customerDao).findCustomerById(any());
       Customer actual = customerService.findCustomerById(BigDecimal.valueOf(2111));
       assertEquals(customer1,actual);
    }

    @Test
    public void insertCustomer() {
        doNothing().when(customerDao).insertCustomer(any());
        customerService.insertCustomer(customer1);
        Mockito.verify(customerDao, times(1)).insertCustomer(any());
    }

    @Test
    public void updateCustomer() {
        doNothing().when(customerDao).updateCustomer(any());
        customerService.updateCustomer(customer1);
        verify(customerDao, times(1)).updateCustomer(any());
    }

    @Test
    public void deleteCustomer() {
        doNothing().when(customerDao).deleteCustomer(any());
        customerService.deleteCustomer(customer1.getCustNum());
        verify(customerDao, times(1)).deleteCustomer(any());
    }
}