package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entitys.Dish;
import jakarta.annotation.Nonnull;

public class DishDTO {

    @Nonnull
    private final Long id;
    @Nonnull
    private final String name;
    @Nonnull
    private final String description;
    @Nonnull
    private final double price;

    public DishDTO(Long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public DishDTO(Dish dish) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.price = dish.getPrice();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
