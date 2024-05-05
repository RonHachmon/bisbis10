package com.att.tdp.bisbis10.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "dish_order")
@Table(name = "dish_order")
public class DishOrder {

    @Id
    @GeneratedValue
            (
                strategy = GenerationType.IDENTITY
            )
    private Long id;



    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    private Dish dish;

    @JsonIgnore
    @ManyToOne
    private Order order;

    public DishOrder(int quantity, Dish dish, Order order) {
        this.quantity = quantity;
        this.dish = dish;
        this.order = order;
    }

    public DishOrder() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

}
