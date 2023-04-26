package webshop_backend_system.repository;

import webshop_backend_system.model.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepo extends JpaRepository<PurchaseProduct, Long> {

}

