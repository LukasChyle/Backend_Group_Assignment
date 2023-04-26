package webshop_backend_system.controller;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webshop_backend_system.model.Product;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.model.PurchaseProduct;
import webshop_backend_system.repository.ProductRepo;
import webshop_backend_system.repository.PurchaseProductRepo;
import webshop_backend_system.repository.PurchaseRepo;

import java.util.List;

@RestController
@RequestMapping("/purchaseProducts")
public class PurchaseProductController {
    private static final Logger log = LoggerFactory.getLogger(PurchaseProductController.class);
    private final PurchaseProductRepo repo;
    private final ProductRepo productRepo;
    private final PurchaseRepo purchaseRepo;

    public PurchaseProductController
            (PurchaseProductRepo repo, ProductRepo productRepo, PurchaseRepo purchaseRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
    }

    @RequestMapping
    public List<PurchaseProduct> getPurchaseItems() {
        return repo.findAll();
    }

    @RequestMapping("/{id}")
    public PurchaseProduct getPurchaseItemById(@PathVariable("id") Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
    }

    @RequestMapping("/add")
    public String addPurchaseItem(@RequestParam int quantity, double price, Long productId, Long purchaseId) {
        if (productRepo.findById(productId).isPresent()) {
            if (purchaseRepo.findById(purchaseId).isPresent()) {
                Product product = productRepo.findById(productId).get();
                Purchase purchase = purchaseRepo.findById(purchaseId).get();
                repo.save(new PurchaseProduct(quantity, price, product, purchase));
                log.info("Item added to purchase");
                return "Item added to purchase.";
            }
            return "Purchase id not valid.";
        }
        return "Product id not valid.";
    }

    @RequestMapping("/delete/{id}")
    public String deletePurchaseItemById(@PathVariable("id") Long id) {
        if (repo.findById(id).isPresent()) {
            PurchaseProduct p = repo.findById(id).get();
            repo.deleteById(id);
            log.info("Item removed from  purchase: " + p);
            return "Item removed from  purchase: " + p;
        }
        return "Item with id= " + id + " not found";
    }

}
