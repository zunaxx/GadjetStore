package peaksoft.api;

import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.services.BasketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
@Tag(name = "BasketApi")
public class BasketApi {

    private final BasketService basketService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    SimpleResponse saveBasket(@RequestParam List<Long> productIds) {
        return basketService.saveBasket(productIds);
    }

}
