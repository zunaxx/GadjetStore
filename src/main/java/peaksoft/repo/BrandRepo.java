package peaksoft.repo;

import peaksoft.dto.response.BrandResponse;
import peaksoft.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepo extends JpaRepository<Brand,Long> {
   @Query("select new peaksoft.dto.response.BrandResponse(b.id,b.brandName,b.image) from Brand b")
    List<BrandResponse> findAllBrands();

    Optional<BrandResponse> getBrandById(Long id);
}
