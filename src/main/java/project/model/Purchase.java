package project.model;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String zipCode;
    private String locality;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private @Setter(AccessLevel.NONE) Instant dateCreated;
    @UpdateTimestamp
    private @Setter(AccessLevel.NONE) Instant dateUpdated;

//    @ManyToOne
//    @JoinColumn
//    @JsonIgnore
//    private Customer customer;
//
//    @OneToMany(mappedBy = "purchase")
//    @JoinTable
//    private List<PurchaseProduct> purchaseProducts;

}

//5.15
/*POJO -
Purchase: id,
address,
zipCode,
locality,
createdDate,
updatedDate,
@ManyToOne:Customer,
@OneToMan:List<PurchaseProduct>

2h*/