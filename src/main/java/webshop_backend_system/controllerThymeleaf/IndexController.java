package webshop_backend_system.controllerThymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class IndexController {
    @RequestMapping
    public String getIndex(Model model) {
        model.addAttribute("productsBut", "Products");
        model.addAttribute("customersBut", "Customers");
        model.addAttribute("purchasesBut", "Purchases");
        model.addAttribute("purchaseProductBut", "Purchased Products");
        model.addAttribute("header", "Submission task for backend course");
        model.addAttribute("message", "\"Fake-store\" backend project " +
                "made by: Jonathan Hellgren, Dennis Fridström and Lukas Chyle");
        return "index";
    }
}
