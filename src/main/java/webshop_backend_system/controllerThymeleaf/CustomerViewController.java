package webshop_backend_system.controllerThymeleaf;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webshop_backend_system.model.Customer;
import webshop_backend_system.repository.CustomerRepo;

import java.util.List;


@Controller
@RequestMapping("/thymeleaf/customers")
public class CustomerViewController {

    private final CustomerRepo customerRepo;

    public CustomerViewController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @RequestMapping("/view")
    public String viewAllCustomers(Model model) {
        List<Customer> customerList = customerRepo.findAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("headerCustomer", "All Customers");
        return "CustomerView";
    }

    @RequestMapping({"/form", "/form/{id}"})
    public String getForm(@PathVariable(required = false) Long id, Model model) {
        if (id != null && customerRepo.findById(id).isPresent()) {
            model.addAttribute("updateCustomer", customerRepo.findById(id).get());
            model.addAttribute("header", "Update Customer");
            model.addAttribute("addBut", "Update Customer");
        } else {
            model.addAttribute("header", "Add Customer");
            model.addAttribute("addBut", "Add Customer");
        }
        return "CustomerForm";
    }
    @PostMapping("/formPost")
    public String customerFormPost(@RequestParam String ssn, @RequestParam String fName,
                                   @RequestParam String lName, @RequestParam String phone,
                                   @RequestParam String email, @RequestParam(required = false) Long id, Model model){

        customerRepo.save(new Customer(id,ssn,fName,lName,phone,email));

        return viewAllCustomers(model);
    }
    @RequestMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable Long id, Model model) {
        customerRepo.deleteById(id);
        return viewAllCustomers(model);
    }

}
