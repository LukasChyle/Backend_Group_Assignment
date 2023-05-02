package webshop_backend_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webshop_backend_system.model.Customer;


import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}
