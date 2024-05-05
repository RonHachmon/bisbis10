package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderDTO {
    @NotNull
    private final long restaurantId;

    @NotNull
    private final DishOrderDTO [] orderItems;

    public OrderDTO(long restaurantId, DishOrderDTO[] orderItems) {
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
