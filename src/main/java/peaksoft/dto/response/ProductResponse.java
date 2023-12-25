package peaksoft.dto.response;

import peaksoft.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponse {
    private Long id;

    private String name;

    private BigDecimal price;

    private List<String> images;

    private String characteristic;

    private boolean isFavorite;

    private String madeIn;

    private Category category;

    private String comment;
    private String brand;


    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, boolean isFavorite, String madeIn, Category category, List<String> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
        this.images = images;
        this.comment = comment;
    }

    public ProductResponse(Long id, String name, BigDecimal price, List<String> images, String characteristic, boolean isFavorite, String madeIn, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.category = category;
    }

}
