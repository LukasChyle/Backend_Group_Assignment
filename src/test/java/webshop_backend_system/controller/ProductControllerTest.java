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
import webshop_backend_system.model.Product;
import webshop_backend_system.repository.ProductRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductController controller;

    @MockBean
    private ProductRepo mockProductRepo;

    @BeforeEach
    void init() {
        Product p1 = new Product(1L, "Tekanna","Kinesisk stil",499, 26);
        Product p2 = new Product(2L, "Bordsduk","Motiv: blå blommor",199.99, 58);
        Product p3 = new Product(3L, "Ravioli",
                "Äkta italiensk ravioli med oxkött, konservburk 500g",49.99, 64);

        when(mockProductRepo.findById(1L)).thenReturn(Optional.of(p1));
        when(mockProductRepo.findById(2L)).thenReturn(Optional.of(p2));
        when(mockProductRepo.findById(3L)).thenReturn(Optional.of(p3));
        when(mockProductRepo.findAll()).thenReturn(Arrays.asList(p1, p2, p3));
    }

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/noneURL")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void getAllProducts() throws Exception {
        List<Product> p = mockProductRepo.findAll();
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));
    }

    @Test
    void getProduct() throws Exception {
        Product p = mockProductRepo.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(1L)));
        this.mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));
    }

    @Test
    void addProduct() throws Exception {
        Product p1 = new Product("titel","description",1, 2);
        this.mockMvc.perform(post("/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p1)))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Added: " + p1)));
    }

    @Test
    void deleteProductById() throws Exception {
        this.mockMvc.perform(get("/products/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Deleted: Product(id=1, title=Tekanna," +
                        " description=Kinesisk stil, price=499.0, balance=26, dateCreated=null, dateUpdated=null)")));

        this.mockMvc.perform(get("/products/delete/5"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Product with id= 5 not found")));
    }
}