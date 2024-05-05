package com.att.tdp.bisbis10.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderDTO {
    @NotNull(message = "Restaurant ID is required")
    private final long restaurantId;


    @Valid
    @NotNull(message = "orderItems array field is required")
    @NotEmpty(message = "orderItems array field cannot be empty")
    private final DishOrderDTO [] orderItems;

    public OrderDTO(long restaurantId,@Valid DishOrderDTO[] orderItems) {
        this.restaurantId = restaurantId;
        this.orderItems = orderItems;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public DishOrderDTO[] getOrderItems() {
        return orderItems;
    }
}
