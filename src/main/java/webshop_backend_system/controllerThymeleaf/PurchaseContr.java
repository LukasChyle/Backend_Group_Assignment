package webshop_backend_system.controllerThymeleaf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webshop_backend_system.model.Customer;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.repository.CustomerRepo;
import webshop_backend_system.repository.PurchaseRepo;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf/purchases")
public class PurchaseContr {
    private static final Logger log = LoggerFactory.getLogger(PurchaseContr.class);
    private final PurchaseRepo repo;
    private final CustomerRepo customerRepo;

    public PurchaseContr
            (PurchaseRepo repo, CustomerRepo customerRepo) {
        this.repo = repo;
        this.customerRepo = customerRepo;
    }

    @RequestMapping({"", "/{id}"})
    public String getPurchases(@PathVariable(required = false) Long id, Model model) {
        if (id != null && customerRepo.findById(id).isPresent()) {
            Customer customer = customerRepo.findById(id).get();
            List<Purchase> list = repo.findByCustomer(customer);
            model.addAttribute("list", list);
            model.addAttribute("header", "Purchases to Customer id: " + id);
            model.addAttribute("view", id);
        } else {
            List<Purchase> list = repo.findAll();
            model.addAttribute("list", list);
            model.addAttribute("header", "Purchases");
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
        return "showPurchase";
    }

    @RequestMapping({"/form", "/form/{id}"})
    public String getForm(@PathVariable(required = false) Long id, Model model) {
        if (id != null && repo.findById(id).isPresent()) {
            model.addAttribute("purchase", repo.findById(id).get());
            model.addAttribute("header", "Update purchase " + id);
            model.addAttribute("addBut", "Update");
        } else {
            model.addAttribute("header", "Create purchase");
            model.addAttribute("addBut", "Create");
        }
        return "formPurchase";
    }

    @PostMapping("/add")
    public String addPurchase(@RequestParam(required = false) Long id,
                              @RequestParam(defaultValue = "") String address,
                              @RequestParam(defaultValue = "") String zipCode,
                              @RequestParam(defaultValue = "") String locality,
                              @RequestParam(defaultValue = "0") Long customerId, Model model) {
        if (customerRepo.findById(customerId).isPresent()) {
            if (id != null && repo.findById(id).isPresent()) {
                model.addAttribute("formMessage", "Updated purchase");
                log.info("Updated purchase id: " + id);
            } else {
                model.addAttribute("formMessage", "Created purchase");
                log.info("Created purchase to customer id: " + customerId);
            }
            Customer customer = customerRepo.findById(customerId).get();
            repo.save(new Purchase(id, address,
                    zipCode, locality, customer));
        } else {
            model.addAttribute("formMessage", "Customer id not valid");
        }
        return getForm(id, model);
    }

    @RequestMapping("/delete/{id}/{view}")
    public String deleteByIdShowByPurchase(@PathVariable Long id, @PathVariable Long view, Model model) {
        if (repo.findById(id).isPresent()) {
            Purchase p = repo.findById(id).get();
            repo.deleteById(id);
            log.info("Deleted purchase: " + p);
        }
        return getPurchases(view, model);
    }
}