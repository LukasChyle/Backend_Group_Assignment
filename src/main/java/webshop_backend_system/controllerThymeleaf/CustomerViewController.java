package webshop_backend_system.controllerThymeleaf;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
        return "CustomerView";
    }

    @RequestMapping("/form")
    public String customerForm(){
        return "CustomerForm";
    }

    @PostMapping("/formPost")
    public String customerFormPost(@RequestParam String ssn, @RequestParam String fName,
                                   @RequestParam String lName, @RequestParam String phone,
                                   @RequestParam String email, Model model){
        customerRepo.save(new Customer(ssn,fName,lName,phone,email));
        return viewAllCustomers(model);
    }
    @RequestMapping("/viewAll")
    public String viewAllCustomers(Model model) {
        List<Customer> customerList = customerRepo.findAll();
        model.addAttribute("customerList", customerList);
        model.addAttribute("headerCustomer", "All Customers");
        return "CustomerView";
    }
    @RequestMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable long id, Model model) {
        customerRepo.deleteById(id);
        return viewAllCustomers(model);
    }
    @RequestMapping("/update/{id}")
    public String updateCustomerById(@PathVariable long id, Model model) {
        model.addAttribute("updateCustomer" , customerRepo.findById(id).get());
        return "CustomerUpdateForm";
    }
    @PostMapping("/formUpdate")
    public String updateCustomerByForm(@RequestParam long id, @RequestParam String ssn,
                                       @RequestParam String fName, @RequestParam String lName,
                                       @RequestParam String phone, @RequestParam String email, Model model) {
        Customer customer = customerRepo.findById(id).get();
        customer.setFirstName(fName);
        customer.setLastName(lName);
        customer.setSsn(ssn);
        customer.setPhone(phone);
        customer.setEmail(email);
        return viewAllCustomers(model);
    }
    @RequestMapping("/fullInfo/{id}")
    public String openFullInfo(@PathVariable long id, Model model) {
        model.addAttribute("fullInfoCustomer" , customerRepo.findById(id).get());
        model.addAttribute("headerFull", "Chosen Customer");
        return "CustomerFullInfo";
    }
}
