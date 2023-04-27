package webshop_backend_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import webshop_backend_system.model.Customer;
import webshop_backend_system.repository.CustomerRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepo mockRepo;

    @Autowired
    private CustomerController customerController;

    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

        Customer c1 = new Customer("8683489059", "Ash", "Ketchum", "07332567827", "GottaCatchEmAll@pikapika.com");
        Customer c2 = new Customer("8006315692", "Harry", "Potter", "07628496979", "TheCh0sen1@hogwarts.owl");
        Customer c3 = new Customer("8375508938", "Frodo", "Baggins", "07046070106", "R1ngBearer@theshire.org");

        when(mockRepo.findById(1L)).thenReturn(Optional.of(c1));
        when(mockRepo.findById(2L)).thenReturn(Optional.of(c2));
        when(mockRepo.findById(3L)).thenReturn(Optional.of(c3));
        when(mockRepo.findAll()).thenReturn(Arrays.asList(c1,c2,c3));
        when(mockRepo.findByFirstName("Ash")).thenReturn(List.of(c1));
        when(mockRepo.findByLastName("Ketchum")).thenReturn(List.of(c1));
    }

//    @Test
//    void contextLoads() {
//        assertThat(customerController).isNotNull();
//    }

    @Test
    void shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/noneURL")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void getAllCustomers() throws Exception {
        List<Customer> customerList = mockRepo.findAll();
        this.mockMvc.perform(get("/customers"))
                .andExpect(status().isOk()).andExpect(content().json(objectMapper.writeValueAsString(customerList)));
    }

    @Test
    void findById() throws Exception {
        Customer customer = mockRepo.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(1L)));
        this.mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk()).andExpect(content().json(
                        objectMapper.writeValueAsString(customer)));
    }

    @Test
    void customerByFirstName() throws Exception {
        List<Customer> customerList = mockRepo.findByFirstName("Ash");
        this.mockMvc.perform(get("/customers/Ash/firstName"))
                .andExpect(status().isOk()).andExpect(content().json(
                        objectMapper.writeValueAsString(customerList)));
    }

    @Test
    void customerByLastName() throws Exception {
        List<Customer> customerList = mockRepo.findByLastName("Ketchum");
        this.mockMvc.perform(get("/customers/Ketchum/lastName"))
                .andExpect(status().isOk()).andExpect(content().json(
                        objectMapper.writeValueAsString(customerList)));
    }

    @Test
    void deleteCustomerById() throws Exception {
        this.mockMvc.perform(get("/customers/1/delete"))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("Customer with id 1 have been deleted")));
    }

    @Test
    void addCustomer() throws Exception {
        this.mockMvc.perform(post("/customers/add").contentType(MediaType.APPLICATION_JSON).content("{\"ssn\":\"6612185947\"," +
                        "\"firstName\":\"Daniel\",\"lastName\":\"Larusso\"," +
                        "\"phone\":\"07638458592\",\"email\":\"WaxOnWaxOf@Miyagidokarate.com\"}"))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("Customer Daniel Larusso have been added")));
    }

    @Test
    void addCustomerWithParams() throws Exception {
        this.mockMvc.perform(get(
                "/customers/addWithParams?ssn=6709204759&firstName=Johnny&lastName=Lawrence&phone=0704579384&email=NoMercy@Eaglefangkarate.org"))
                .andExpect(status().isOk()).andExpect(content().string(equalTo("Customer Johnny Lawrence have been added")));
    }
}