package com.pankaj.zomatosearchapi_assignment.api;

import com.pankaj.zomatosearchapi_assignment.models.CuisinesResponseModel;
import com.pankaj.zomatosearchapi_assignment.models.RestaurantSearchResponseModel;
import com.pankaj.zomatosearchapi_assignment.retrofit.RetrofitClient;
import com.pankaj.zomatosearchapi_assignment.retrofit.ZomatoAPI;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

import static com.pankaj.zomatosearchapi_assignment.utilities.Utils.getOrderBy;
import static com.pankaj.zomatosearchapi_assignment.utilities.Utils.getSortBy;


/**
 * This is an entry point for all API calls
 *
 * @author Pankaj Nirban
 * @since 16-06-2019
 */
public class APIs {
    private Callbacks callbacks;
    private ZomatoAPI zomatoAPI;

    public APIs(Callbacks callbacks) {
        this.callbacks = callbacks;
        zomatoAPI = RetrofitClient.getInstance().create(ZomatoAPI.class);
    }

    public void getCuisines(int cityId) {
        Observable<CuisinesResponseModel> response = zomatoAPI.getCuisines(cityId);
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<CuisinesResponseModel>() {
                    @Override
                    public void onNext(CuisinesResponseModel cuisinesResponseModel) {
                        callbacks.onSuccess(cuisinesResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbacks.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getRestaurantsByQuery(String query, String sortBy, String orderBy, int offset) {
        Observable<RestaurantSearchResponseModel> response = zomatoAPI.getRestaurants(query, sortBy, orderBy, offset);
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<RestaurantSearchResponseModel>() {
                    @Override
                    public void onNext(RestaurantSearchResponseModel restaurantSearchResponseModel) {
                        callbacks.onSuccess(restaurantSearchResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbacks.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMoreRestaurantsByQueryAndCuisine(String query, int cuisine_id, int offset) {
        ZomatoAPI zomatoAPI = RetrofitClient.getInstance().create(ZomatoAPI.class);
        Observable<RestaurantSearchResponseModel> response = zomatoAPI.getRestaurantsFilterByCuisine(query, cuisine_id, getSortBy(), getOrderBy(), offset);
        response.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<RestaurantSearchResponseModel>() {
                    @Override
                    public void onNext(RestaurantSearchResponseModel restaurantSearchResponseModel) {
                        callbacks.onSuccess(restaurantSearchResponseModel);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callbacks.onFailure(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface Callbacks {
        void onStart();

        void onSuccess(Object response);

        void onFailure(Throwable t);
    }
}
