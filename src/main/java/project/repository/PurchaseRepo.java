package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.Purchase;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
}
