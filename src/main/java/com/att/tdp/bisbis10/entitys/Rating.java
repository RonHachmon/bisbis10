package com.att.tdp.bisbis10.entitys;

import jakarta.persistence.*;

import java.util.Objects;


@Entity(name = "rating")
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    @Column(name = "rate", nullable = false)
    private double rate;

    public Rating(double rate, Restaurant restaurant) {
        this.restaurant = restaurant;
        this.rate = rate;
    }

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public double getRate() {
        return rate;
    }

}
