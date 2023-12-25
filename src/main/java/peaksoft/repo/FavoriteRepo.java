package peaksoft.repo;

import peaksoft.dto.response.FavoriteResponse;
import peaksoft.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite,Long> {

    @Query("select new peaksoft.dto.response.FavoriteResponse(f.id,f.product,f.user) from Favorite f")
    List<FavoriteResponse> findAllFavorites();

    @Query("SELECT f FROM Favorite f WHERE f.product.id = :productId AND f.user.id = :userId")
    Optional<Favorite> findByProductAndUser(Long productId, Long userId);

    Optional<FavoriteResponse> findFavoriteById(Long id);
}
