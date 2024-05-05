package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entitys.Cuisine;
import com.att.tdp.bisbis10.entitys.Dish;
import com.att.tdp.bisbis10.entitys.Restaurant;

import java.util.Set;


public class RestaurantWithDishDTO {

    final long id;

    final String name;

    final double averageRating;


    final boolean isKosher;


    final String[] cuisines;

    final DishDTO[] dishes;


    public RestaurantWithDishDTO(long id, String name, double averageRating, boolean isKosher, String[] cuisines, DishDTO[] dishes) {
        this.id = id;
        this.name = name;
        this.averageRating = averageRating;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        this.dishes = dishes;
    }

    public RestaurantWithDishDTO(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.averageRating = restaurant.getRating();
        this.isKosher = restaurant.getKosher();
        this.cuisines = new String[restaurant.getCuisines().size()];
        addCuisines(restaurant.getCuisines());
        this.dishes = new DishDTO[restaurant.getDishes().size()];
        addDishes(restaurant.getDishes());


    }

    private void addDishes(Set<Dish> dishes) {
        int i = 0;
        for (Dish dish : dishes) {
            this.dishes[i++] = new DishDTO(dish);
        }

    }

    private void addCuisines(Set<Cuisine> cuisines) {
        int i = 0;
        for (Cuisine cuisine : cuisines) {
            this.cuisines[i++] = cuisine.name();
        }

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public String[] getCuisines() {
        return cuisines;
    }

    public DishDTO[] getDishes() {
        return dishes;
    }
}
