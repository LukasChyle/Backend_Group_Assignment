package webshop_backend_system.controllerThymeleaf;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webshop_backend_system.controller.PurchaseProductController;
import webshop_backend_system.model.Product;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.model.PurchaseProduct;
import webshop_backend_system.repository.ProductRepo;
import webshop_backend_system.repository.PurchaseProductRepo;
import webshop_backend_system.repository.PurchaseRepo;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf/purchaseProducts")
public class PurchaseProductContr {
    private static final Logger log = LoggerFactory.getLogger(PurchaseProductController.class);
    private final PurchaseProductRepo repo;
    private final ProductRepo productRepo;
    private final PurchaseRepo purchaseRepo;

    public PurchaseProductContr
            (PurchaseProductRepo repo, ProductRepo productRepo, PurchaseRepo purchaseRepo) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
    }

    @RequestMapping
    public String getAll(Model model) {
        List<PurchaseProduct> list = repo.findAll();
        model.addAttribute("purchaseProductsList", list);
        model.addAttribute("headerPurchaseProduct", "Purchased Products");
        model.addAttribute("view", (long) -1);
        baseLayoutSetup(model);
        return "showPurchaseProducts";
    }

    @RequestMapping("/{id}")
    public String getAllByPurchase(@PathVariable Long id, Model model) {
        if (purchaseRepo.findById(id).isPresent()) {
            Purchase purchase = purchaseRepo.findById(id).get();
            List<PurchaseProduct> list = repo.findByPurchase(purchase);
            model.addAttribute("purchaseProductsList", list);
            model.addAttribute("headerPurchaseProduct", "Products to purchase id: " + id);
        }
        model.addAttribute("view", id);
        baseLayoutSetup(model);
        return "showPurchaseProducts";
    }

//    @RequestMapping("/{id}")
//    public PurchaseProduct getPurchaseProductById(@PathVariable Long id) {
//        return repo.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
//    }

//    @RequestMapping("/add")
//    public String addPurchaseProduct(@RequestParam int quantity, Long productId, Long purchaseId) {
//        if (productRepo.findById(productId).isPresent()) {
//            if (purchaseRepo.findById(purchaseId).isPresent()) {
//                Product product = productRepo.findById(productId).get();
//                Purchase purchase = purchaseRepo.findById(purchaseId).get();
//                repo.save(new PurchaseProduct(product.getTitle(), quantity, product.getPrice(), product, purchase));
//                log.info("Product added to purchase");
//                return "Product added to purchase.";
//            }
//            return "Purchase id not valid.";
//        }
//        return "Product id not valid.";
//    }

    @RequestMapping("/delete/{id}")
    public String deleteByIdShowByPurchase(@PathVariable Long id, Model model) {
        if (repo.findById(id).isPresent()) {
            PurchaseProduct p = repo.findById(id).get();
            repo.deleteById(id);
            log.info("Product removed from  purchase: " + p);
        }
            return getAll(model);
    }

//    @RequestMapping("/delete/{id}/{view}")
//    public String deleteByIdShowByPurchase(@PathVariable Long id, @PathVariable Long view, Model model) {
//        if (repo.findById(id).isPresent()) {
//            PurchaseProduct p = repo.findById(id).get();
//            repo.deleteById(id);
//            log.info("Product removed from  purchase: " + p);
//        }
//        if (view == -1) {
//            return getAll(model);
//        }
//        return getAllByPurchase(view, model);
//    }

    private void baseLayoutSetup(Model model) {
        model.addAttribute("productsBut", "Products");
        model.addAttribute("customersBut", "Customers");
        model.addAttribute("purchasesBut", "Purchases");
        model.addAttribute("addProductBut", "Create Product");
        model.addAttribute("addCustomerBut", "Create Customer");
        model.addAttribute("addPurchaseBut", "Create Purchase");
        model.addAttribute("addPurchaseProductBut", "Add Product To Purchase");
    }
}
