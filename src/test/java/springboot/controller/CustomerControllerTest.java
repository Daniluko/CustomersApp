package springboot.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import springboot.entity.Customer;

import javax.sql.DataSource;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by Danylo on 02.04.2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class CustomerControllerTest {
    private static final int STATUS_OK = 200;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private DataSource dataSource;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setup() {
        Resource initSchema = new ClassPathResource("scripts\\schema.sql");
        Resource data = new ClassPathResource("scripts\\data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/customers")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
        List<Customer> orders = getListCustomerResult(mvcResult);
        assertEquals(2, orders.size());
    }

    @Test
    public void testGetCustomerByIdExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/customers/{id}", "1111")).andDo(print()).andReturn();
        Customer customer = mapper.readValue(mvcResult.getResponse().getContentAsString(), Customer.class);
        assertEquals(STATUS_OK , mvcResult.getResponse().getStatus());
        assertNotNull(customer);
    }

    @Test
    public void testGetCustomerByIdNotExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/customers/{id}", "-1")).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
        assertTrue(mvcResult.getResponse().getContentAsString().length() == 0);
    }

    @Test
    public void testInsertCustomer() throws Exception {
        String json = mapper.writeValueAsString(new Customer());
        MvcResult mvcResult = mockMvc.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/customers/{id}", "1111")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    @Test
    public void testUpdateCustomerByIdExist() throws Exception {
        MvcResult mvcResult = mockMvc.perform(put("/customers/{id}", "1111").param("credit_limit", "777")).andDo(print()).andReturn();
        assertEquals(STATUS_OK, mvcResult.getResponse().getStatus());
    }

    private List<Customer> getListCustomerResult(MvcResult mvcResult)
            throws IOException, JsonParseException, JsonMappingException, UnsupportedEncodingException {
        return mapper.readValue(mvcResult.getResponse().getContentAsString(),
                mapper.getTypeFactory().constructCollectionType(List.class, Customer.class));
    }
}
