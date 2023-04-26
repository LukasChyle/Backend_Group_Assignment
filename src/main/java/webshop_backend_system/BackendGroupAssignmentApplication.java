package webshop_backend_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import webshop_backend_system.model.Customer;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.repository.CustomerRepo;
import webshop_backend_system.repository.PurchaseRepo;

@SpringBootApplication
public class BackendGroupAssignmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendGroupAssignmentApplication.class, args);
    }



//    @Bean
//    CommandLineRunner initData(PurchaseRepo purchaseRepo, CustomerRepo customerRepo) {
//        return (args) -> {
//
//            Customer c1 = new Customer("2039", "Karl", "Karlsson", "070-1234567", "Butte@kutte");
//            Customer c2 = new Customer("2040", "Kalle", "Karlsson", "070-1234567", "Butte@kutte");
//            Customer c3 = new Customer("2041", "Karl", "Karlsson", "070-1234567", "Butte@kutte");
//
//            customerRepo.save(c1);
//            customerRepo.save(c2);
//            customerRepo.save(c3);
//
//            Purchase p1 = new Purchase("Gulaggatan", "11822", "Stockholm Sweden", c1);
//            Purchase p2 = new Purchase("Karlbergsvägen", "11327", "Stockholm Sweden", c2);
//            Purchase p3 = new Purchase("Kungsgatan", "11143", "Stockholm Sweden", c3);
//
//            purchaseRepo.save(p1);
//            purchaseRepo.save(p2);
//            purchaseRepo.save(p3);
//        };
//    }
}

