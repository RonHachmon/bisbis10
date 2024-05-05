package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.execption.ResourceIDNotFoundException;
import com.att.tdp.bisbis10.dto.RestaurantDTO;
import com.att.tdp.bisbis10.dto.RestaurantUpdateDTO;
import com.att.tdp.bisbis10.dto.RestaurantWithDishDTO;
import com.att.tdp.bisbis10.entitys.Cuisine;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> getRestaurantsByCuisines(List<String> cuisines) {
        Set<Cuisine> cuisineSet = new HashSet<>();
        for (String cuisine : cuisines) {
            cuisineSet.add(Cuisine.getCuisineByString(cuisine));
        }
        return restaurantRepository.findByCuisinesIn(cuisineSet);
    }

    public RestaurantWithDishDTO getRestaurantById(long id) {
        Restaurant restaurant = getRestaurantIfExist(id);


        return new RestaurantWithDishDTO(restaurant);
    }

    public Restaurant createRestaurant(RestaurantDTO restaurantDTO) {
        Set<Cuisine> cuisineSet = new HashSet<>();
        for (String cuisine : restaurantDTO.getCuisines()) {
            cuisineSet.add(Cuisine.getCuisineByString(cuisine));
        }
        Restaurant restaurant = new Restaurant(restaurantDTO.getName(), restaurantDTO.getKosher(), cuisineSet);
        return restaurantRepository.save(restaurant);
    }

    public Restaurant updateRestaurant(long id, RestaurantUpdateDTO restaurantUpdateDTO) {
        Restaurant restaurant = getRestaurantIfExist(id);
        updateRestaurant(restaurant, restaurantUpdateDTO);

        return restaurantRepository.save(restaurant);
    }

    private void updateRestaurant(Restaurant restaurant, RestaurantUpdateDTO restaurantUpdateDTO) {
        if (restaurantUpdateDTO.getKosher() != null) {
            restaurant.setKosher(restaurantUpdateDTO.getKosher());
        }
        if (restaurantUpdateDTO.getName() != null) {
            restaurant.setName(restaurantUpdateDTO.getName());
        }
        updateCuisine(restaurant, restaurantUpdateDTO);
    }

    public void deleteRestaurant(long id) {
        getRestaurantIfExist(id);
        restaurantRepository.deleteById(id);
    }

    public Restaurant getRestaurantIfExist(long id) {
        return restaurantRepository.findById(id).orElseThrow(() -> new ResourceIDNotFoundException("Restaurant", id));
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }


    private void updateCuisine(Restaurant restaurant, RestaurantUpdateDTO restaurantUpdateDTO) {
        Set<Cuisine> newCuisineSet = new HashSet<>(restaurant.getCuisines());
        if (restaurantUpdateDTO.getDeleteCuisines() != null) {
            newCuisineSet.removeIf(cuisine -> cuisineExistInList(cuisine, restaurantUpdateDTO.getDeleteCuisines()));
        }
        if (restaurantUpdateDTO.getNewCuisines() != null) {
            for (String newCuisine : restaurantUpdateDTO.getNewCuisines()) {
                newCuisineSet.add(Cuisine.getCuisineByString(newCuisine));
            }
        }
        restaurant.setCuisines(newCuisineSet);
    }

    private boolean cuisineExistInList(Cuisine cuisine, String[] cuisines) {
        for (String cuisineString : cuisines) {
            if (cuisine.name().equalsIgnoreCase(cuisineString)) {
                return true;
            }
        }
        return false;
    }


}
