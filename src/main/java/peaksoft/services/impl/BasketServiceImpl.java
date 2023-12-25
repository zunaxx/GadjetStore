package peaksoft.services.impl;

import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.entities.Basket;
import peaksoft.entities.Product;
import peaksoft.entities.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.BasketRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.services.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final UserRepo userRepo;
    private final BasketRepo basketRepo;
    private final ProductRepo productRepo;

    @Override
    public SimpleResponse saveBasket(List<Long> productId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User with email: %s not found".formatted(email)));
        List<Product> products = new ArrayList<>();
        for (Long l : productId) {
            products.add(productRepo.findProductById(l).orElseThrow(
                    () -> new NotFoundException("User with id: %s not found".formatted(l))
            ));
        }
        Basket basket = Basket
                .builder()
                .products(products)
                .user(user)
                .build();
        basketRepo.save(basket);
        log.info("Basket successfully S a v e d");
        return SimpleResponse
                .builder()
                .message("Products successfully saved")
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
