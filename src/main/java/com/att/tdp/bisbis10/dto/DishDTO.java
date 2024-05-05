package com.att.tdp.bisbis10.dto;

import com.att.tdp.bisbis10.entitys.Dish;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DishDTO {

    private final Long id;
    @NotBlank
    private final String name;
    @NotNull
    private final String description;
    @Positive(message = "Price must be positive")
    private final Integer price;

    public DishDTO(Long id, String name, String description, Integer price) {
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

    public Integer getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }
}
