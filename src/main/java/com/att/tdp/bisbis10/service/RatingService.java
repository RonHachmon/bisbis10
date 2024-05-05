package com.att.tdp.bisbis10.service;


import com.att.tdp.bisbis10.dto.RatingDTO;
import com.att.tdp.bisbis10.entitys.Rating;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final RestaurantService restaurantService;

    @Autowired
    public RatingService(RatingRepository ratingRepository, RestaurantService restaurantService) {
        this.ratingRepository = ratingRepository;
        this.restaurantService = restaurantService;
    }

    public void addRating(RatingDTO ratingDTO) {
        Restaurant restaurant = restaurantService.getRestaurantIfExist(ratingDTO.getRestaurantID());
        Rating rating = new Rating(ratingDTO.getRate(), restaurant);
        restaurant.addToTotalRate(rating.getRate());
        restaurant.increaseNumberOfRates(1);
        ratingRepository.save(rating);
        restaurantService.saveRestaurant(restaurant);
    }


}
