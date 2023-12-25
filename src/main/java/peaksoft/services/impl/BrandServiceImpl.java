package peaksoft.services.impl;

import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.response.BrandResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.entities.Brand;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.BrandRepo;
import peaksoft.services.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService {

    private final BrandRepo brandRepo;

    @Override
    public List<BrandResponse> findAllBrands() {
        return brandRepo.findAllBrands();
    }

    @Override
    public BrandResponse save(BrandRequest brandRequest) {
        Brand brand=new Brand();
        brand.setBrandName(brandRequest.brandName());
        brand.setImage(brandRequest.image());
        brandRepo.save(brand);
        log.info("Brand successfully saved");
        return new BrandResponse(
                brand.getId(),
                brand.getBrandName(),
                brand.getImage());
    }

    @Override
    public BrandResponse getBrandById(Long id) {
        return brandRepo.getBrandById(id).orElseThrow(()->
                new NotFoundException(String.format("Brand with id: %s not found",id)));
    }

    @Override
    public SimpleResponse updateBrand(Long id, BrandRequest brandRequest) {
        Brand brand = brandRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Brand with id: " + id + " not found"));
        brand.setBrandName(brandRequest.brandName());
        brand.setImage(brandRequest.image());
        brandRepo.save(brand);
        log.info("Brand is updated");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Brand successfully updated")
                .build();
    }

    @Override
    public SimpleResponse deleteBrand(Long id) {
      if (!brandRepo.existsById(id)){
          throw new NotFoundException(String.format("Brand with id: %s not found",id));
      }
      brandRepo.deleteById(id);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Brand with id: "+id+" is deleted")
                .build();
    }
}
