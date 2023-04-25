package webshop_backend_system.model;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.List;

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
//    private Customer customer;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private @Setter(AccessLevel.NONE) Instant dateCreated;
    @UpdateTimestamp
    private @Setter(AccessLevel.NONE) Instant dateUpdated;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseProduct> purchaseProducts;

//    @ManyToOne(optional = false)
//    @JoinColumn
//    @JsonIgnore
//    private Customer customer;

//    @OneToMany(mappedBy = "purchase")
//    private List<PurchaseProduct> purchaseProducts;

    public Purchase(String address, String zipCode, String locality/*, Customer customer*/) {
        this.address = address;
        this.zipCode = zipCode;
        this.locality = locality;
//        this.customer = customer;
    }
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