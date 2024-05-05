package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class RatingDTO {

    @NotNull(message = "restaurantid field is required")
    private final Long restaurantId;
    @NotNull(message = "rating field is required")
    @Min(1)
    @Max(5)
    private final Double rating;


    public RatingDTO(double rating, long restaurantId) {
        this.rating = rating;
        this.restaurantId = restaurantId;
    }

    public double getRate() {
        return rating;
    }

    public long getRestaurantID() {
        return restaurantId;
    }
}
