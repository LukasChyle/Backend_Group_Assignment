package com.example.backend_group_assignment;

import com.example.backend_group_assignment.controller.PurchaseController;
import com.example.backend_group_assignment.model.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.transform.Result;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PurchaseController purchaseController;

    @Test
    public void testPurchaseController() {
        assert purchaseController != null;
    }

    @Test
    public void testPurchase() {
        ResponseEntity<List<Purchase>> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/purchase/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        List<Purchase> purchases = response.getBody();
        assertNotNull(purchases);
        assertFalse(purchases.isEmpty());
    }

    @Test
    public void testPurchaseById() {
        ResponseEntity<Purchase> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/purchase/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Purchase purchase = response.getBody();
        assertNotNull(purchase);
        assertEquals(1, purchase.getId());
    }
}

//06:15