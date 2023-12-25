package peaksoft.entities;

import peaksoft.enums.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product extends BaseEntity{

    private String name;

    private BigDecimal price;

    @ElementCollection
    private List<String>images;

    private String characteristic;

    private boolean isFavorite;

    private String madeIn;

    @Enumerated(EnumType.STRING)
    private Category category;

//    @ToString.Exclude
    @ManyToOne
    private Brand brand;

//    @ToString.Exclude
    @ManyToMany(mappedBy = "products")
    private List<Basket>baskets;

//    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<Comment>comments;

//    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<Favorite>favorites;








}
