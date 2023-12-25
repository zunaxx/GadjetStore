package peaksoft.services.impl;

import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;
import peaksoft.entities.Favorite;
import peaksoft.entities.Product;
import peaksoft.entities.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repo.FavoriteRepo;
import peaksoft.repo.ProductRepo;
import peaksoft.repo.UserRepo;
import peaksoft.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepo favoriteRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public List<FavoriteResponse> findAllFavorites() {
        return favoriteRepo.findAllFavorites();
    }


    @Override
    public SimpleResponse save(Long productId, FavoriteRequest favoriteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        User user = userRepo.getUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User with email: " + email + " not found"));
        Product product = productRepo.findProductById(productId).orElseThrow(() ->
                new NotFoundException("Product with id: " + productId + " not found"));
        Favorite favorite=favoriteRequest.build();
        favorite.setProduct(product);
        user.addFavorite(favorite);
        favoriteRepo.save(favorite);
        log.info("Favorite successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("THIS PERSON LIKED THIS PRODUCT")
                .build();


    }

    @Override
    public FavoriteResponse getFavoriteById(Long id) {
        return favoriteRepo.findFavoriteById(id).orElseThrow(() ->
                new NotFoundException("Favorite with id: " + id + " not found"));

    }

    @Override
    public SimpleResponse deleteFavorite(Long id) {
        if (!favoriteRepo.existsById(id)){
            throw new NotFoundException("Favorite with id: "+id+" not found");
        }
        favoriteRepo.deleteById(id);
        log.info("Favorite is deleted");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Favorite with id: "+id+" is D e l e t e d")
                .build();
    }

//    @Override
//    public FavoriteResponse getFavoriteById(Long id) {
//        return favoriteRepo.getFavoriteById(id).orElseThrow(()->
//                new NotFoundException(String.format("Favorite with id: %s not found",id)));
//    }

}
