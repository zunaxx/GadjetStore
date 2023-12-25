package peaksoft.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "comments")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseEntity{


    private String comment;

    private LocalDate createdEt;

    @ManyToOne
    private Product product;

    @ManyToOne
    private User user;



}
