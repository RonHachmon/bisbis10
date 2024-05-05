package com.att.tdp.bisbis10.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class DishOrderDTO {
    @NotNull
    private final long dishId;
    @NotNull
    @Min(1)
    private final int amount;

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
