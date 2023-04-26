package webshop_backend_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop_backend_system.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
