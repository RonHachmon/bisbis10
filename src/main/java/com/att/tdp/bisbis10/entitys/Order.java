package com.att.tdp.bisbis10.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "order")
@Table(name = "restaurant_order")
public class Order {
    @Id
    @GeneratedValue(
            strategy = GenerationType.UUID
    )
    private UUID id;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<DishOrder> dishOrders;

    public Order() {
    }

    public Order(UUID id, Restaurant restaurant, List<DishOrder> dishOrders) {
        this.id = id;
        this.restaurant = restaurant;
        this.dishOrders = dishOrders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<DishOrder> getDishOrders() {
        return dishOrders;
    }

    public void setDishOrders(List<DishOrder> dishOrders) {
        this.dishOrders = dishOrders;
    }

    public void addDishOrder(DishOrder dishOrder) {
        if(dishOrders == null)
        {
            dishOrders = new ArrayList<>();
        }
        dishOrders.add(dishOrder);
    }
}
