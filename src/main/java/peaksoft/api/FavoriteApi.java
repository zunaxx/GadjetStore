package peaksoft.api;

import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.services.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@Tag(name = "FavoriteApi")
public class FavoriteApi {

    private final FavoriteService favoriteService;

    @PermitAll
    @GetMapping
    @Operation(summary = "Get All",description = "Get All favorite info")
    public List<FavoriteResponse> findAllFavorites(){
        return favoriteService.findAllFavorites();
    }

    @Secured({"ADMIN","USER"})
    @PostMapping("/{productId}")
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    public SimpleResponse save(@PathVariable Long productId,
                               @RequestBody FavoriteRequest favoriteRequest){
        return favoriteService.save(productId,favoriteRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted",description = "To delete  fill all the fields!")
    public SimpleResponse deleteFavorite(@PathVariable Long id){
        return favoriteService.deleteFavorite(id);
    }
}
