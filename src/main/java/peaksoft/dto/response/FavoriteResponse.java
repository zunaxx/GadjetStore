package peaksoft.dto.response;

import peaksoft.entities.Product;
import peaksoft.entities.User;
import lombok.Builder;

@Builder
public record FavoriteResponse(
        Long id,
        Product product,
        User user
) {
    public FavoriteResponse(Long id, Product product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }
}
