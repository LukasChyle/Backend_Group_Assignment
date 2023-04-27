package webshop_backend_system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import webshop_backend_system.model.Customer;
import webshop_backend_system.model.Product;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.model.PurchaseProduct;
import webshop_backend_system.repository.ProductRepo;
import webshop_backend_system.repository.PurchaseProductRepo;
import webshop_backend_system.repository.PurchaseRepo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseProductControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseProductController controller;

    @MockBean
    private PurchaseProductRepo mockPurchaseProductRepo;

    @MockBean
    private PurchaseRepo mockPurchaseRepo;

    @MockBean
    private ProductRepo mockProductRepo;

    @BeforeEach
    void init() {
        Product product = new Product("","",0, 0);
        Customer customer = new Customer("","","","","");
        Purchase purchase = new Purchase("","","", customer);
        PurchaseProduct p1 = new PurchaseProduct(
                 5, 200, product, purchase);
        PurchaseProduct p2 = new PurchaseProduct(
                 1, 99.99, product, purchase);
        PurchaseProduct p3 = new PurchaseProduct(
                 4, 50, product, purchase);

        when(mockPurchaseProductRepo.findById(1L)).thenReturn(Optional.of(p1));
        when(mockPurchaseProductRepo.findById(2L)).thenReturn(Optional.of(p2));
        when(mockPurchaseProductRepo.findById(3L)).thenReturn(Optional.of(p3));
        when(mockPurchaseProductRepo.findAll()).thenReturn(Arrays.asList(p1, p2, p3));
        when(mockPurchaseRepo.findById(1L)).thenReturn(Optional.of(purchase));
        when(mockProductRepo.findById(1L)).thenReturn(Optional.of(product));
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
    void getPurchaseItems() throws Exception {
        List<PurchaseProduct> p = mockPurchaseProductRepo.findAll();
        this.mockMvc.perform(get("/purchaseProducts"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));
    }

    @Test
    void getPurchaseItemById() throws Exception {
        PurchaseProduct p = mockPurchaseProductRepo.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(1L)));
        this.mockMvc.perform(get("/purchaseProducts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p)));
    }

    @Test
    void addPurchaseItem() throws Exception {
        this.mockMvc.perform(get("/purchaseProducts/add?quantity=1&price=199&productId=1&purchaseId=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Product added to purchase.")));

        this.mockMvc.perform(get("/purchaseProducts/add?quantity=1&price=199&productId=2&purchaseId=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Product id not valid.")));

        this.mockMvc.perform(get("/purchaseProducts/add?quantity=1&price=199&productId=1&purchaseId=2"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Purchase id not valid.")));
    }

    @Test
    void deletePurchaseItemById() throws Exception {
        this.mockMvc.perform(get("/purchaseProducts/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Product removed from  purchase:" +
                        " PurchaseProduct(id=null, quantity=5, price=200.0, dateCreated=null," +
                        " dateUpdated=null, product=Product(id=null, title=, description=," +
                        " price=0.0, balance=0, dateCreated=null, dateUpdated=null), purchase=Purchase(id=null," +
                        " address=, zipCode=, locality=, dateCreated=null, dateUpdated=null, purchaseProducts=null," +
                        " customer=Customer(id=0, ssn=, firstName=, lastName=, phone=, email=, purchases=null," +
                        " dateCreated=null, dateUpdated=null)))")));

        this.mockMvc.perform(get("/purchaseProducts/delete/5"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Purchased product with id= 5 not found")));
    }
}