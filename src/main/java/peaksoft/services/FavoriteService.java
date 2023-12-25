package peaksoft.services;

import peaksoft.dto.request.FavoriteRequest;
import peaksoft.dto.response.FavoriteResponse;
import peaksoft.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface FavoriteService {
    List<FavoriteResponse> findAllFavorites();

    SimpleResponse save(Long productId, FavoriteRequest favoriteRequest);


    FavoriteResponse getFavoriteById(Long id);

    SimpleResponse deleteFavorite(Long id);
}
