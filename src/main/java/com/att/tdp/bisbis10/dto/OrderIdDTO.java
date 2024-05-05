package com.att.tdp.bisbis10.dto;

import java.util.UUID;

public class OrderIdDTO {
    private final String orderId;
    public OrderIdDTO(UUID orderId) {
        this.orderId = String.valueOf(orderId);
    }

    public String getOrderId() {
        return orderId;
    }

}
