package peaksoft.services;

import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.response.BrandResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface BrandService {


    List<BrandResponse> findAllBrands();

    BrandResponse save(BrandRequest brandRequest);

    BrandResponse getBrandById(Long id);

    SimpleResponse updateBrand(Long id, BrandRequest brandRequest);

    SimpleResponse deleteBrand(Long id);
}
