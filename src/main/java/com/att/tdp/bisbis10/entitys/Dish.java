package com.att.tdp.bisbis10.entitys;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity(name = "dish")
@Table(name = "dish")
public class Dish {
    @Id
    @GeneratedValue
            (
                    strategy = GenerationType.IDENTITY
            )
    private Long id;
    @Column(name = "dish_name", nullable = false, length = 60)
    private String name;
    @Column(name = "dish_description", length = 200)
    private String description;
    @Column(name = "dish_price", nullable = false)
    private double price;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public Dish(String name, String description, double price, Restaurant restaurant) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }

    public Dish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }
}
