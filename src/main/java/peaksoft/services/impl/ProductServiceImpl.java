package peaksoft.services.impl;

import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.entities.Brand;
import peaksoft.entities.Product;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.BrandRepo;
import peaksoft.repo.CommentRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final CommentRepo commentRepo;


    // Get all products иштебей жатат
//    @Override
//    public List<ProductResponse> getAllByImages() {
//        return productRepo.getAllByImages();
//    }


    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepo.getAllByImages();
    }

    @Override
    public SimpleResponse save(ProductRequest productRequest, Long brandId) {
        Brand brand = brandRepo.findById(brandId).orElseThrow(() ->
                new NotFoundException("Brand with id: " + brandId + " not found"));

        Product product = new Product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setFavorite(productRequest.isFavorite());
        product.setMadeIn(productRequest.madeIn());
        product.setCategory(productRequest.category());
        product.setBrand(brand);
        productRepo.save(product);
        log.info("Product successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product successfully S a v e d")
                .build();
    }

    @Override
    public SimpleResponse update(Long id, ProductRequest productRequest) {
        Product product = productRepo.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Product with id: %s not found", id)));
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setFavorite(true);
        product.setMadeIn(productRequest.madeIn());
        product.setCategory(productRequest.category());
        log.info("Product successfully updated");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product successfully updated")
                .build();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Product with id: " + id + " not found"));
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setImages(product.getImages());
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setFavorite(true);
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    @Override
    public SimpleResponse deleteProduct(Long id) {
       if (!productRepo.existsById(id)){
           throw new NotFoundException("Product with id: "+id+ "not found");
       }
       productRepo.deleteById(id);
       log.info("Product is deleted");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id: "+id+" is D e l e t e d")
                .build();
    }

    @Override
    public PaginationResponse getAllPagination(int currentPage, int pageSize) {
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);
        Page<ProductResponse> products= productRepo.findAllProducts(pageable);
        return PaginationResponse.builder()
                .t(Collections.singletonList(products.getContent()))
                .currentPage(products.getNumber())
                .pageSize(products.getTotalPages())
                .build();
    }

}