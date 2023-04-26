package webshop_backend_system.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import webshop_backend_system.repository.CustomerRepo;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepo mockRepo;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void findById() {
    }

    @Test
    void customerByFirstName() {
    }

    @Test
    void customerByLastName() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void addCustomer() {
    }

    @Test
    void addCustomerWithParams2() {
    }
}