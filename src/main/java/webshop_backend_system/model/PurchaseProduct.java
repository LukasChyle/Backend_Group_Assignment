package webshop_backend_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PurchaseProduct {
    @Id @GeneratedValue
    private Long id;
    private int quantity;
    private double price;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private @Setter(AccessLevel.NONE) Instant dateCreated;
    @UpdateTimestamp
    private @Setter(AccessLevel.NONE) Instant dateUpdated;

//    @ManyToOne(optional = false) @JoinColumn
//    private Product product;

    @ManyToOne(optional = false) @JoinColumn @JsonIgnore
    private Purchase purchase;

//    public PurchaseProduct(int quantity, double price, Product product, Purchase purchase) {
//        this.quantity = quantity;
//        this.price = price;
//        this.product = product;
//        this.purchase = purchase;
//    }


}