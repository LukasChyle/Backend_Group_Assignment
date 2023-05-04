package webshop_backend_system.controller;

import org.junit.jupiter.api.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import webshop_backend_system.model.Purchase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PurchaseControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PurchaseController purchaseController;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testPurchaseController() {
        assert purchaseController != null;
    }

    @Test
    public void testPurchase() {
        ResponseEntity<List<Purchase>> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/purchases",
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
                "http://localhost:" + port + "/purchases/1",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        Purchase purchase = response.getBody();
        assertNotNull(purchase);
        assertEquals(1, purchase.getId());
    }

    @Test

    public void testInvalidPurchaseById() {
        ResponseEntity<Purchase> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/purchases/999999",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        Purchase purchase = response.getBody();
        assert purchase != null;
        assertNull(purchase.getId());
    }

    @Test
    public void testAddPurchase() throws InterruptedException {
        ResponseEntity<String> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/purchases/add?address=Test&zipcode=Test&locality=Test&customerId=1",
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<>() {
                });

        String message = response.getBody();
        assertNotNull(message);
        assertEquals("Purchase added", message);
        cleanTestInputs();
    }

    @Test
    public void testInvalidAddPurchase() {
        ResponseEntity<String> response = testRestTemplate.exchange(
                "http://localhost:" + port + "/purchases/add?address=Test&zipcode=Test&locality=Test&customerId=999999999999",
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<>() {
                });

        String message = response.getBody();
        assertNotNull(message);
        assertEquals("Customer id not valid", message);
    }

    public void cleanTestInputs() throws InterruptedException {
        Thread.sleep(1000);
        jdbcTemplate.update(
                "DELETE FROM purchase WHERE address = ?",
                "Test");
    }
}