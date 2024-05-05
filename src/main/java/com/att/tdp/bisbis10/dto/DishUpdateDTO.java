package com.att.tdp.bisbis10.dto;


import jakarta.validation.constraints.Positive;

public class DishUpdateDTO {


    private final String name;

    private final String description;


    @Positive(message = "Price must be positive")
    private final Integer price;

    public DishUpdateDTO(String name, String description, Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
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
}
