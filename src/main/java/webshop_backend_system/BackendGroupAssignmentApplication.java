package webshop_backend_system;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import webshop_backend_system.model.Customer;
import webshop_backend_system.model.Product;
import webshop_backend_system.model.Purchase;
import webshop_backend_system.model.PurchaseProduct;
import webshop_backend_system.repository.CustomerRepo;
import webshop_backend_system.repository.ProductRepo;
import webshop_backend_system.repository.PurchaseProductRepo;
import webshop_backend_system.repository.PurchaseRepo;

@SpringBootApplication
public class BackendGroupAssignmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendGroupAssignmentApplication.class, args);
    }


//    @Bean
//    CommandLineRunner initData(PurchaseRepo purchaseRepo, CustomerRepo customerRepo,
//                               ProductRepo productRepo, PurchaseProductRepo purchaseProductRepo) {
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
//            Product pr1 = new Product("Chorizo", "Big spicy sausage", 50, 100);
//            Product pr2 = new Product("Salami", "Big spicy salami", 50, 100);
//            Product pr3 = new Product("Pepperoni", "Spicy vegetable", 50, 100);
//            Product pr4 = new Product("Mozzarella", "Cow cheese", 50, 100);
//            Product pr5 = new Product("Parmesan", "Cow cheese", 50, 100);
//
//            productRepo.save(pr1);
//            productRepo.save(pr2);
//            productRepo.save(pr3);
//            productRepo.save(pr4);
//            productRepo.save(pr5);
//
//
//            Purchase p1 = new Purchase("Gulaggatan", "11822", "Stockholm Sweden", c1);
//            Purchase p2 = new Purchase("Karlbergsvägen", "11327", "Stockholm Sweden", c2);
//            Purchase p3 = new Purchase("Kungsgatan", "11143", "Stockholm Sweden", c3);
//
//            purchaseRepo.save(p1);
//            purchaseRepo.save(p2);
//            purchaseRepo.save(p3);
//
//            PurchaseProduct pp1 = new PurchaseProduct(1, 50, pr1, p1);
//            PurchaseProduct pp2 = new PurchaseProduct(1, 50, pr2, p1);
//            PurchaseProduct pp3 = new PurchaseProduct(2, 100, pr3, p1);
//            PurchaseProduct pp4 = new PurchaseProduct(1, 50, pr2, p2);
//            PurchaseProduct pp5 = new PurchaseProduct(1, 50, pr5, p3);
//
//            purchaseProductRepo.save(pp1);
//            purchaseProductRepo.save(pp2);
//            purchaseProductRepo.save(pp3);
//            purchaseProductRepo.save(pp4);
//            purchaseProductRepo.save(pp5);
//
//
//        };
//    }
}

