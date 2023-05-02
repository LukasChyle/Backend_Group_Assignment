package webshop_backend_system.controllerThymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webshop_backend_system.model.Product;
import webshop_backend_system.repository.ProductRepo;

import java.util.List;

@Controller
@RequestMapping("/thymeleaf/products")
public class ProductsViewController {
    private final ProductRepo productRepo;

    public ProductsViewController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @RequestMapping({""})
    public String showProducts(Model model) {
        List<Product> productList = productRepo.findAll();
        model.addAttribute("productList", productList);
        model.addAttribute("headerProduct", "All Products");
        return "showProducts";
    }

    @RequestMapping({"/form", "/form/{id}"})
    public String getForm(@PathVariable(required = false) Long id, Model model) {
        if (id != null && productRepo.findById(id).isPresent()) {
            model.addAttribute("product", productRepo.findById(id).get());
            model.addAttribute("header", "Update product");
            model.addAttribute("addBut", "Update");
        } else {
            model.addAttribute("header", "Add product");
            model.addAttribute("addBut", "Add");
        }
        return "formProducts";
    }

    @PostMapping("/form/add")
    public String addProduct(@RequestParam(required = false) Long id, @RequestParam String title,
                             @RequestParam Double price, @RequestParam String description,
                             @RequestParam Integer balance, Model model) {
        if (id != null && productRepo.findById(id).isPresent()) {
            model.addAttribute("formMessage", "Updated product");
        } else {
            model.addAttribute("formMessage", "Added product");
        }
        productRepo.save(new Product(id, title, description, price, balance));
        return getForm(id, model);
    }

    @RequestMapping("/delete/{id}")
    public String deleteProductById(@PathVariable Long id, Model model) {
        productRepo.deleteById(id);
        return showProducts(model);
    }
}

