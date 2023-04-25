package com.example.backend_group_assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendGroupAssignmentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendGroupAssignmentApplication.class, args);
    }

//    @Bean
//    CommandLineRunner initData(PurchaseRepo purchaseRepo) {
//        return (args) -> {
//            Purchase p1 = new Purchase("Gulaggatan", "11822", "Stockholm Sweden");
//            Purchase p2 = new Purchase("Karlbergsvägen", "11327", "Stockholm Sweden");
//            Purchase p3 = new Purchase("Kungsgatan", "11143", "Stockholm Sweden");
//
//            purchaseRepo.save(p1);
//            purchaseRepo.save(p2);
//            purchaseRepo.save(p3);
//        };
//    }
}
