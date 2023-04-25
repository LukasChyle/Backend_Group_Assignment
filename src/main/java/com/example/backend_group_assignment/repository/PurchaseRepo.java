package com.example.backend_group_assignment.repository;

import com.example.backend_group_assignment.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepo extends JpaRepository<Purchase,Long> {
}
