package com.att.tdp.bisbis10.configuration;


import com.att.tdp.bisbis10.entitys.Cuisine;
import com.att.tdp.bisbis10.entitys.Dish;
import com.att.tdp.bisbis10.entitys.Rating;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import com.att.tdp.bisbis10.repository.RatingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;


import java.util.List;
import java.util.Set;

@Configuration
public class RestaurantConfig {

    @Bean
    @Order(1)
    CommandLineRunner addingRestaurant(RestaurantRepository restaurantRepository) {

        return args -> {
            if (restaurantRepository.findAll().size() == 0) {
                Restaurant japanika = new Restaurant("Japanika", true, Set.of(Cuisine.Asian));
                Restaurant taizu = new Restaurant("Taizu", false,
                        Set.of(Cuisine.Asian, Cuisine.Mexican, Cuisine.Indian));
                restaurantRepository.saveAll(List.of(japanika, taizu));
            }
        };
    }

    @Bean
    @Order(2)
    CommandLineRunner addingDish(DishRepository dishRepository, RestaurantRepository restaurantRepository) {

        return args -> {
            if (dishRepository.findAll().size() == 0) {
                Restaurant japanika = restaurantRepository.findById(1L).get();
                Restaurant taizu = restaurantRepository.findById(2L).get();
                Dish noodles = new Dish("Noodle", "Amazing one", 59, japanika);
                Dish sushi = new Dish("Sushi", "Straight from japan", 32, japanika);
                Dish chicken = new Dish("Chicken", "Mega crispy", 62, taizu);

                dishRepository.saveAll(List.of(noodles, sushi, chicken));
            }

        };
    }

    @Bean
    @Order(3)
    CommandLineRunner addingRating(RestaurantRepository restaurantRepository, RatingRepository ratingRepository) {

        return args -> {
            if (ratingRepository.findAll().size() == 0) {
                Restaurant japanika = restaurantRepository.findById(1L).get();
                Restaurant taizu = restaurantRepository.findById(2L).get();
                ratingRepository.saveAll(List.of(createRating(4.0, japanika), createRating(3.0, japanika),
                        createRating(3.0, taizu), createRating(4.0, taizu), createRating(5.0, taizu)));
                restaurantRepository.saveAll(List.of(japanika, taizu));

            }
        };
    }

    private Rating createRating(double rate, Restaurant restaurant) {
        Rating rating = new Rating(rate, restaurant);
        restaurant.increaseNumberOfRates(1);
        restaurant.addToTotalRate(rate);
        return rating;
    }

}
