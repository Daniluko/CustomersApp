package springboot.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.entity.Customer;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Danylo on 02.04.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CustomerDaoImplTest {

    private static final Customer NOT_EXIST_CUSTOMER_INSERT = new Customer(BigDecimal.valueOf(333333));
    private static final Customer ALREADY_EXIST_CUSTOMER_UPDATE = new Customer(BigDecimal.valueOf(1111));
    private static final Customer ALREADY_EXIST_CUSTOMER_DELETE = new Customer(BigDecimal.valueOf(222222));
    private static final BigDecimal NOT_EXIST_CUSTOMER_ID = BigDecimal.valueOf(-1);

    @Autowired
    private CustomerDaoImpl customerDao;
    @Autowired
    private DataSource dataSource;

    @Before
    public void initDb() {
        Resource initSchema = new ClassPathResource("scripts\\schema.sql");
        Resource data = new ClassPathResource("scripts\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
    }

    @Test
    public void testGetAllCustomers() {
        Set<Customer> customers = customerDao.getAllCustomers();
        assertTrue(customers.size() >= 2);
    }

    @Test
    public void testFindCustomerById() {
        Customer customer = customerDao.findCustomerById(ALREADY_EXIST_CUSTOMER_UPDATE.getCustNum());
        assertNotNull(customer);
    }

    @Test
    public void testFindCustomerByIdNotExist(){
        Customer customer = customerDao.findCustomerById(NOT_EXIST_CUSTOMER_ID);
        assertNull(customer);
    }

    @Test
    public void testInsertCustomer() {
        customerDao.insertCustomer(NOT_EXIST_CUSTOMER_INSERT);
    }

    @Test
    public void updateCustomer() {
        ALREADY_EXIST_CUSTOMER_UPDATE.setCreditLimit(BigDecimal.valueOf(123));
        customerDao.updateCustomer(ALREADY_EXIST_CUSTOMER_UPDATE);
    }

    @Test
    public void deleteCustomer() {
        customerDao.deleteCustomer(ALREADY_EXIST_CUSTOMER_DELETE.getCustNum());
    }
}