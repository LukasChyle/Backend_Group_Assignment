package webshop_backend_system.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;
    private String ssn;
    private String firstName;
    private String lastName;

//    private String fullName = firstName + lastName;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private @Setter(AccessLevel.NONE) Instant dateCreated ;
    @UpdateTimestamp
    private @Setter(AccessLevel.NONE) Instant dateUpdated ;

    public Customer(String ssn, String firstName, String lastName, String phone, String email) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }



}
