package project.controller;

import org.springframework.web.bind.annotation.*;
import project.model.Purchase;
import project.repository.PurchaseRepo;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepo purchaseRepo;

    public PurchaseController(PurchaseRepo purchaseRepo) {
        this.purchaseRepo = purchaseRepo;
    }

    @RequestMapping("/")
    public List<Purchase> getAllPurchases() {
        return purchaseRepo.findAll();
    }


    @RequestMapping("/{id}")
    public Purchase getPurchaseById(@PathVariable Long id) {
        return purchaseRepo.findById(id).get();
    }

//    @PostMapping("/add")
//    public String addPurchase(@RequestBody Purchase purchase) {
//        purchaseRepo.save(purchase);
//        return "Purchase added " + purchase.getKund() + " Added";
//
//    }

    @RequestMapping("/delete/{id}")
    public String deletePurchase(@PathVariable Long id) {
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