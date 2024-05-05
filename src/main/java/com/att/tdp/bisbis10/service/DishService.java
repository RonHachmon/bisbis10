package com.att.tdp.bisbis10.service;

import com.att.tdp.bisbis10.dto.DishDTO;
import com.att.tdp.bisbis10.dto.DishUpdateDTO;
import com.att.tdp.bisbis10.entitys.Dish;
import com.att.tdp.bisbis10.entitys.Restaurant;
import com.att.tdp.bisbis10.execption.ResourceIDNotFoundException;
import com.att.tdp.bisbis10.repository.DishRepository;
import com.att.tdp.bisbis10.utils.Validation;
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
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Dish dish = new Dish(dishDTO.getName(), dishDTO.getDescription(), dishDTO.getPrice(), restaurant);
        dishRepository.save(dish);
    }

    public List<Dish> getRestaurantDishes(Long restaurantID) {
        restaurantService.getRestaurantById(restaurantID);
        return dishRepository.findByRestaurantId(restaurantID);

    }

    public void deleteDish(Long restaurantID, Long dishID) {
        validateDishBelongToRestaurant(restaurantID, dishID);
        dishRepository.deleteById(dishID);
    }



    public Dish getDishById(Long dishID) {
        return dishRepository.findById(dishID).orElseThrow(() -> new ResourceIDNotFoundException("Dish", dishID));
    }

    public void updateDish(Long restaurantID, Long dishID, DishUpdateDTO dishUpdateDTO) {

        validateDishBelongToRestaurant(restaurantID, dishID);
        Dish dish = getDishById(dishID);
        updateDish(dish, dishUpdateDTO);
        dishRepository.save(dish);

    }

    private void updateDish(Dish dish, DishUpdateDTO dishUpdateDTO) {
        if (dishUpdateDTO.getDescription() != null) {
            dish.setDescription(dishUpdateDTO.getDescription());
        }
        if (Validation.isNameValid(dishUpdateDTO.getName())) {
            dish.setName(dishUpdateDTO.getName());
        }
        if (Validation.isPriceValid(dishUpdateDTO.getPrice())) {
            dish.setPrice(dishUpdateDTO.getPrice());
        }
    }
    public void validateDishBelongToRestaurant(Long restaurantID, Long dishID) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantID);
        if (!restaurant.getDishes().contains(getDishById(dishID))) {
            {
                throw new IllegalArgumentException("Dish id " + dishID +
                        " does not belong to restaurant id " + restaurantID );
            }
        }
    }
}
