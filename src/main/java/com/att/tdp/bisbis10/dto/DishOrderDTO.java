package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DishOrderDTO {
    @NotNull(message = "dishId field is required")
    private final Long dishId;
    @NotNull(message = "dishId field is required")
    @Positive(message = "amount must be positive")
    private final Integer amount;

    public DishOrderDTO(long dishId, int amount) {
        this.dishId = dishId;
        this.amount = amount;
    }

    public long getDishId() {
        return dishId;
    }

    public int getAmount() {
        return amount;
    }
}
