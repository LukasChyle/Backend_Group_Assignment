package project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.model.Customer;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    public List<Customer> findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);

//    public List<Customer> findByFullName(String firstName, String lastName);
}
