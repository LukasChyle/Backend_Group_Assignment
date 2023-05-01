package webshop_backend_system.controllerThymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import webshop_backend_system.model.Customer;
import webshop_backend_system.repository.CustomerRepo;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerViewController {

    private final CustomerRepo customerRepo;

    public CustomerViewController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @RequestMapping("/view")
    public String customerView(){
        return "customerView";
    }

    @RequestMapping("/form")
    public String customerForm(){
        return "customerForm";
    }

    @PostMapping("/formPost")
    public String customerFormPost(@RequestParam String ssn, @RequestParam String fName,
                                   @RequestParam String lName, @RequestParam String phone,
                                   @RequestParam String email){
//        model.addAttribute("ssn", ssn);
//        model.addAttribute("fName", fName);
//        model.addAttribute("lName", lName);
//        model.addAttribute("phone", phone);
//        model.addAttribute("email", email);
        customerRepo.save(new Customer(ssn,fName,lName,phone,email));
        return "customerView";
    }
    @RequestMapping("/viewAll")
    public String viewAllCustomers(Model model) {
        List<Customer> customerList = customerRepo.findAll();
        model.addAttribute("allCustomers", customerList);
        return "customerView";
    }
}
