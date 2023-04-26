package webshop_backend_system.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import webshop_backend_system.repository.PurchaseProductRepo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseProductControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseProductController controller;

    @MockBean
    private PurchaseProductRepo mockRepo;

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
    }

    @Test
    void getPurchaseItemById() throws Exception {
    }

    @Test
    void addPurchaseItem() throws Exception {
    }

    @Test
    void deletePurchaseItemById() throws Exception {
    }
}