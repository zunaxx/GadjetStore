package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "baskets")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Basket extends BaseEntity {

    @ManyToMany
    private List<Product>products;



    @OneToOne
    private User user;

    @Column(name = "quantity", nullable = false, columnDefinition = "integer default 0")
    private int quantity;



    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
