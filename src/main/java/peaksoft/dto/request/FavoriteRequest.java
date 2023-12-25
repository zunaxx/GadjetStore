package peaksoft.dto.request;

import peaksoft.entities.Favorite;
import lombok.Builder;

@Builder
public record FavoriteRequest(
        Long id

) {

    public Favorite build() {
        Favorite favorite= new Favorite();
        favorite.setId(this.id);
        return favorite;
    }
}
