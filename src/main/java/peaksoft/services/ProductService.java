package peaksoft.services;

import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    SimpleResponse save(ProductRequest productRequest, Long brandId);

    SimpleResponse update(Long id, ProductRequest productRequest);

    ProductResponse getProductById(Long id);
    SimpleResponse deleteProduct(Long id);

    PaginationResponse getAllPagination(int currentPage, int pageSize);



}
