package project.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private String phone;
    private String email;
    // TODO: Behöver varje customer en adress??
//    private String address;

//    @Column(nullable = false, updatable = false)
//    @CreationTimestamp
//    private @Setter(AccessLevel.NONE) Instance dateCreated ;
//    @UpdateTimestamp
//    private @Setter(AccessLevel.NONE) Instance dateUpdated ;

    public Customer(String ssn, String firstName, String lastName, String phone, String email) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
    }



}
