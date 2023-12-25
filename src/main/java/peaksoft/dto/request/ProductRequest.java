package peaksoft.dto.request;

import peaksoft.enums.Category;
import jakarta.persistence.ElementCollection;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record ProductRequest(
         String name,

         BigDecimal price,

        @ElementCollection
         List<String>images,

         String characteristic,

         boolean isFavorite,

         String madeIn,
         Category category
) {

    public ProductRequest {
    }
}
