package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDTO;
import com.att.tdp.bisbis10.dto.DishUpdateDTO;
import com.att.tdp.bisbis10.entitys.Dish;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.execption.ResourceIDNotFoundException;
import com.att.tdp.bisbis10.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final RestaurantService restaurantService;


    @Autowired
    public DishService(DishRepository dishRepository, RestaurantService restaurantService) {
        this.dishRepository = dishRepository;
        this.restaurantService = restaurantService;
    }


    public void addDish(Long restaurantId, DishDTO dishDTO) {
        Restaurant restaurant = restaurantService.getRestaurantIfExist(restaurantId);
        Dish dish = new Dish(dishDTO.getName(), dishDTO.getDescription(), dishDTO.getPrice(), restaurant);
        dishRepository.save(dish);
    }

    public List<Dish> getRestaurantDishes(Long restaurantID) {
        restaurantService.getRestaurantIfExist(restaurantID);
        return dishRepository.findByRestaurantId(restaurantID);

    }

    public void deleteDish(Long restaurantID, Long dishID) {
        restaurantService.getRestaurantIfExist(restaurantID);
        getDishIfExist(dishID);
        dishRepository.deleteById(dishID);
    }

    public Dish getDishIfExist(Long dishID) {
        return dishRepository.findById(dishID).orElseThrow(() -> new ResourceIDNotFoundException("Dish", dishID));
    }

    public void updateDish(Long restaurantID, Long dishID, DishUpdateDTO dishUpdateDTO) {


        restaurantService.getRestaurantIfExist(restaurantID);
        Dish dish = getDishIfExist(dishID);
        updateDish(dish, dishUpdateDTO);
        dishRepository.save(dish);

    }

    private void updateDish(Dish dish, DishUpdateDTO dishUpdateDTO) {
        if (dishUpdateDTO.getDescription() != null) {
            dish.setDescription(dishUpdateDTO.getDescription());
        }
        if (dishUpdateDTO.getName() != null) {
            dish.setName(dishUpdateDTO.getName());
        }
        if (dishUpdateDTO.getPrice() != null) {
            dish.setPrice(dishUpdateDTO.getPrice());
        }
    }
}
