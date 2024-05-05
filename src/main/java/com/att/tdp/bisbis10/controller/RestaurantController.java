package com.att.tdp.bisbis10.controller;


import com.att.tdp.bisbis10.dto.RestaurantDTO;
import com.att.tdp.bisbis10.dto.RestaurantUpdateDTO;
import com.att.tdp.bisbis10.dto.RestaurantWithDishDTO;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.service.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;


    @Autowired
    RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping()
    ResponseEntity<List<Restaurant>> getRestaurants(@RequestParam Optional<String> cuisine) {
        // Filter restaurants based on cuisine if present, otherwise return all restaurants
        List<Restaurant> restaurants;
        if (cuisine.isPresent()) {
            restaurants = restaurantService.getRestaurantsByCuisines(List.of(cuisine.get()));
        } else {
            restaurants = restaurantService.getAllRestaurants();
        }
        return ResponseEntity.ok(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantWithDishDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantWithDishDTO restaurantWithDishDTO = restaurantService.getRestaurantWithDishes(id);
        return ResponseEntity.ok(restaurantWithDishDTO);

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> createRestaurant(@RequestBody @Valid RestaurantDTO restaurant) {
        restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantUpdateDTO restaurantUpdateDTO) {
        restaurantService.updateRestaurant(id, restaurantUpdateDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

}
