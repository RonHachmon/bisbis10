package com.att.tdp.bisbis10.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RestaurantDTO {
    @NotBlank(message = "name field cannot be empty or null")
    final String name;

    @NotNull(message = "isKosher field is required")
    final boolean isKosher;

    @NotNull(message = "cuisines array field is required")
    @NotEmpty(message = "cuisines array field cannot be empty")
    final String[] cuisines;


    public RestaurantDTO(String name, boolean isKosher, String[] cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
    }

    public String getName() {
        return name;
    }

    public boolean getKosher() {
        return isKosher;
    }

    public String[] getCuisines() {
        return cuisines;
    }
}
