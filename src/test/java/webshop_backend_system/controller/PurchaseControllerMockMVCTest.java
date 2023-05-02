package webshop_backend_system.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.repository.PurchaseRepo;

import java.util.Arrays;
import java.util.Optional;


import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerMockMVCTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PurchaseRepo mockRepo;

    @BeforeEach
    public void init() {
        Purchase purchase1 = new Purchase(1L, "address1", "zip1", "locality1", null);
        Purchase purchase2 = new Purchase(2L, "address2", "zip2", "locality2", null);
        Purchase purchase3 = new Purchase(3L, "address3", "zip3", "locality3", null);

        when(this.mockRepo.findById(1L)).thenReturn(Optional.of(purchase1));
        when(this.mockRepo.findById(2L)).thenReturn(Optional.of(purchase2));
        when(this.mockRepo.findById(3L)).thenReturn(Optional.of(purchase3));
        when(this.mockRepo.findAll()).thenReturn(Arrays.asList(purchase1, purchase2, purchase3));
    }

    @Test
    void shouldReturn404() throws Exception {
        this.mockMvc.perform(get("/noneURL")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void getAllPurchases() throws Exception {
        this.mockMvc.perform(get("/purchases"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"address\":\"address1\",\"zipCode\":\"zip1\"," +
                        "\"locality\":\"locality1\",\"dateCreated\":null,\"dateUpdated\":null,\"customer\":null}," +
                        "{\"id\":2,\"address\":\"address2\",\"zipCode\":\"zip2\",\"locality\":\"locality2\"," +
                        "\"dateCreated\":null,\"dateUpdated\":null,\"customer\":null}," +
                        "{\"id\":3,\"address\":\"address3\",\"zipCode\":\"zip3\",\"locality\":\"locality3\"," +
                        "\"dateCreated\":null,\"dateUpdated\":null,\"customer\":null}]"));
    }
    @Test
    public void getPurchaseById() throws Exception {
        this.mockMvc.perform(get("/purchases/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"address\":\"address1\",\"zipCode\":\"zip1\"," +
                        "\"locality\":\"locality1\",\"dateCreated\":null,\"dateUpdated\":null,\"customer\":null}"));
    }

    @Test
    public void addPurchase() throws Exception {
        this.mockMvc.perform(post("/purchases/add?address=address4&zipcode=zip4&locality=locality4&customerId=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Purchase added")));
    }


    @Test
    public void addInvalidPurchase() throws Exception {
        this.mockMvc.perform(post("/purchases/add?address=address4&zipcode=zip4&locality=locality4&customerId=11"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Customer id not valid")));
    }

    @Test
    public void deletePurchaseById() throws Exception {
        this.mockMvc.perform(get("/purchases/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Purchase deleted 1 Deleted")));
    }
}
