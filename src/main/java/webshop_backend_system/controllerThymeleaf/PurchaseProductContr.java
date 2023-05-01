package webshop_backend_system.controllerThymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @RequestMapping({"", "/{id}"})
    public String getPurchaseProducts(@PathVariable(required = false) Long id, Model model) {
        if (id != null && purchaseRepo.findById(id).isPresent()) {
            Purchase purchase = purchaseRepo.findById(id).get();
            List<PurchaseProduct> list = repo.findByPurchase(purchase);
            model.addAttribute("list", list);
            model.addAttribute("header", "Products to purchase id: " + id);
            model.addAttribute("view", id);
        } else {
            List<PurchaseProduct> list = repo.findAll();
            model.addAttribute("list", list);
            model.addAttribute("header", "Purchased Products");
            model.addAttribute("view", (long) 0);
        }
        model.addAttribute("productsBut", "Products");
        model.addAttribute("customersBut", "Customers");
        model.addAttribute("purchasesBut", "Purchases");
        model.addAttribute("purchaseProductBut", "Purchased Products");
        model.addAttribute("addProductBut", "Create Product");
        model.addAttribute("addCustomerBut", "Create Customer");
        model.addAttribute("addPurchaseBut", "Create Purchase");
        model.addAttribute("addPurchaseProductBut", "Add Product To Purchase");
        return "showPurchaseProducts";
    }

    @RequestMapping({"/form", "/form/{id}"})
    public String getForm(@PathVariable(required = false) Long id, Model model) {
        if (id != null && repo.findById(id).isPresent()) {
            model.addAttribute("purchaseProduct", repo.findById(id).get());
            model.addAttribute("header", "Update purchased product");
            model.addAttribute("addBut", "Update");
        } else {
            model.addAttribute("header", "Add product to purchase");
            model.addAttribute("addBut", "Add");
        }
        return "formPurchaseProduct";
    }

    @PostMapping("/add")
    public String addPurchaseProduct(@RequestParam(required = false) Long id,
                                     @RequestParam(defaultValue = "1") int quantity,
                                     @RequestParam(defaultValue = "0") Long productId,
                                     @RequestParam(defaultValue = "0") Long purchaseId, Model model) {
        if (productRepo.findById(productId).isPresent()) {
            if (purchaseRepo.findById(purchaseId).isPresent()) {
                if (id != null && repo.findById(id).isPresent()) {
                    model.addAttribute("formMessage", "Updated purchased product");
                    log.info("Updated purchased product id: " + id);
                } else {
                    model.addAttribute("formMessage", "Added product to purchase");
                    log.info("Added product " + productId + " to purchase id: " + purchaseId);

                }
                Product product = productRepo.findById(productId).get();
                Purchase purchase = purchaseRepo.findById(purchaseId).get();
                repo.save(new PurchaseProduct(id, product.getTitle(),
                        quantity, product.getPrice(), product, purchase));
            } else {
                model.addAttribute("formMessage", "Purchase id not valid");
            }
        } else {
            model.addAttribute("formMessage", "Product id not valid");
        }
        return getForm(id, model);
    }

    @RequestMapping("/delete/{id}/{view}")
    public String deleteByIdShowByPurchase(@PathVariable Long id, @PathVariable Long view, Model model) {
        if (repo.findById(id).isPresent()) {
            PurchaseProduct p = repo.findById(id).get();
            repo.deleteById(id);
            log.info("Product removed from  purchase: " + p);
        }
        return getPurchaseProducts(view, model);
    }
}
