package webshop_backend_system.repository;

import webshop_backend_system.model.Customer;
import webshop_backend_system.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import webshop_backend_system.model.PurchaseProduct;

import java.util.List;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
    List<Purchase> findByCustomer(Customer customer);
}
