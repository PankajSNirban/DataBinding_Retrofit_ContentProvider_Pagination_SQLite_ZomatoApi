package com.pankaj.zomatosearchapi_assignment.retrofit;


import com.pankaj.zomatosearchapi_assignment.models.CuisinesResponseModel;
import com.pankaj.zomatosearchapi_assignment.models.RestaurantSearchResponseModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * This is used for URL detailing
 *
 * @author Pankaj Nirban
 * @since 16-06-2019
 */

public interface ZomatoAPI {

    @GET("api/v2.1/search")
    Observable<RestaurantSearchResponseModel> getRestaurants(@Query("q") String query,
                                                             @Query("sort") String sort,
                                                             @Query("order") String order,
                                                             @Query("start") int offset);

    @GET("api/v2.1/search")
    Observable<RestaurantSearchResponseModel> getRestaurantsFilterByCuisine(@Query("q") String query,
                                                                            @Query("cuisines") int cuisine_id,
                                                                            @Query("sort") String sort,
                                                                            @Query("order") String order,
                                                                            @Query("start") int offset);

    @GET("api/v2.1/cuisines")
    Observable<CuisinesResponseModel> getCuisines(@Query("city_id") int cityId);
}
