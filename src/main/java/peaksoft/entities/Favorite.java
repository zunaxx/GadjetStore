package peaksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "favorites")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite extends BaseEntity{

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

}
