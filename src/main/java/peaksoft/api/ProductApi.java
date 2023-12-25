package peaksoft.api;

import peaksoft.dto.request.ProductRequest;
import peaksoft.dto.response.PaginationResponse;
import peaksoft.dto.response.ProductResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "ProductApi")
public class ProductApi {

    private final ProductService productService;

    @GetMapping("/getAll")  //  Такыр иштебей жатат
    @PermitAll
    @Operation(summary = "Get All",description = "Get All products info")
    public List<ProductResponse> findAllProducts() {
        return productService.getAllProducts();
    }


//    @GetMapping("/getAll")
//    public ResponseEntity<List<ProductResponse>> getAllProducts() {
//        System.out.println("hello");
//        List<ProductResponse> products = productService.getAllProducts();
//        System.out.println("hello2");
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{brandId}")
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    public SimpleResponse save(@PathVariable Long brandId,
                               @RequestBody ProductRequest productRequest) {
        return productService.save(productRequest, brandId);
    }


    @GetMapping("/{id}")
    @Secured({"USER","ADMIN"})
    @Operation(summary = "Get product by ID",description = "To get by ID fill ID!")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @Secured("ADMIN")
    @PutMapping("/{id}")
    @Operation(summary = "Update",description = "To update fill all the fields!")
    public SimpleResponse update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted",description = "To delete  fill all the fields!")
    public SimpleResponse deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }


    @PermitAll
    @GetMapping("/pagination")
    @Operation(summary = "Pagination",description = "To pagination!")
    public ResponseEntity<PaginationResponse> paginationResponse(
            @RequestParam @Min(1) int currentPage,
            @RequestParam @Min(1) int pageSize
    ) {
        if (currentPage < 1 || pageSize < 1) {
            return ResponseEntity.badRequest().build();
        }

        PaginationResponse response = productService.getAllPagination(currentPage, pageSize);
        return ResponseEntity.ok(response);
    }

}
