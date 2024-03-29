package com.pankaj.zomatosearchapi_assignment.models;

import java.util.List;


public class RestaurantSearchResponseModel {
    private int results_found;
    private int results_start;
    private int results_shown;
    private List<RestaurantModel> restaurants;

    public int getResultsFound() {
        return results_found;
    }

    public int getResultsStart() {
        return results_start;
    }

    public int getResultsShown() {
        return results_shown;
    }

    public List<RestaurantModel> getRestaurants() {
        return restaurants;
    }
}
