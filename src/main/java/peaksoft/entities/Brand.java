package peaksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand extends BaseEntity{

    private String brandName;

    private String image;

    @OneToMany(mappedBy = "brand")
    private List<Product>products;

}
