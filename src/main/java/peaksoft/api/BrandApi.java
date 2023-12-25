package peaksoft.api;

import peaksoft.dto.request.BrandRequest;
import peaksoft.dto.response.BrandResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.services.BrandService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
@Tag(name = "BrandApi")
public class BrandApi {

    private final BrandService brandService;

    @PermitAll
    @GetMapping
    @Operation(summary = "Get All",description = "Get All products info")
    public List<BrandResponse> findAllBrand(){
        return brandService.findAllBrands();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    public BrandResponse save(@RequestBody BrandRequest brandRequest){
        return brandService.save(brandRequest);
    }

    @PermitAll
    @GetMapping("/{id}")
    @ApiOperation(value = "Пример операции", response = BrandResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный запрос", response = BrandResponse.class),
            @ApiResponse(code = 400, message = "Некорректный запрос", response = BrandResponse.class),
            @ApiResponse(code = 404, message = "Ресурс не найден", response = BrandResponse.class)})
    @Operation(summary = "Get brand by ID",description = "To get by ID fill ID!")
    public BrandResponse getBrandById(@PathVariable Long id){
        return brandService.getBrandById(id);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    @Operation(summary = "Update",description = "To update fill all the fields!")
    public SimpleResponse updateBrand(@PathVariable Long id,
                                      @RequestBody BrandRequest brandRequest){
        return brandService.updateBrand(id,brandRequest);
    }


    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted",description = "To delete  fill all the fields!")
    public SimpleResponse deleteBrand(@PathVariable Long id){
        return brandService.deleteBrand(id);
    }

}
