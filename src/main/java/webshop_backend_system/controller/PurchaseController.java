package webshop_backend_system.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.repository.CustomerRepo;
import webshop_backend_system.repository.PurchaseRepo;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseRepo purchaseRepo;
    private final CustomerRepo customerRepo;
    private static final Logger LOGGER = Logger.getLogger(PurchaseController.class.getName());

    public PurchaseController(PurchaseRepo purchaseRepo, CustomerRepo customerRepo) {
        this.purchaseRepo = purchaseRepo;
        this.customerRepo = customerRepo;
        }

    @RequestMapping
    public List<Purchase> getAllPurchases() {
        LOGGER.info("getAllPurchases called");
        return purchaseRepo.findAll();
    }


    @RequestMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        LOGGER.info("getPurchaseById called");
        return purchaseRepo.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Purchase with id: " + id + " not found"));
    }

    @PostMapping("/add")
    public String addPurchase(@RequestParam String address, String zipcode
            , String locality, Long customerId) {
        if (customerRepo.findById(customerId).isPresent()) {
            purchaseRepo.save(new Purchase(address, zipcode, locality, customerRepo.findById(customerId).get()));
            LOGGER.info("Purchase added");
            return "Purchase added";
        }
        return "Customer id not valid";
    }

    @RequestMapping("/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
        LOGGER.warning("deletePurchase called");
        purchaseRepo.deleteById(id);
        return "Purchase deleted " + id + " Deleted";
    }

}
// 05:40

/*
Controller -
Purchase
http://localhost:8080/purchase
http://localhost:8080/purchase/{id}
http://localhost:8080/purchase/add
http://localhost:8080/purchase/delete/{id} 4h
 */