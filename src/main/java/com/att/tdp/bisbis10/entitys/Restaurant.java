package com.att.tdp.bisbis10.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.Set;

@Entity(name = "restaurant ")
@Table(name = "restaurant ")
public class Restaurant {
    @Id
    @GeneratedValue
            (
                    strategy = GenerationType.IDENTITY
            )
    private Long id;
    @Column(name = "restaurant_name", nullable = false, length = 60)
    private String name;
    @Column(name = "is_kosher", nullable = false)
    private Boolean isKosher;

    @ElementCollection(targetClass = Cuisine.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "restaurant_cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
    private Set<Cuisine> cuisines;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Dish> dishes;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> ratings;

    @Transient
    private Double averageRating;


    @JsonIgnore
    @Column(name = "total_rate", nullable = false)
    private Double totalRate;


    @JsonIgnore
    @Column(name = "amount_of_rate", nullable = false)
    private Integer numberOfRates;


    public Restaurant() {
    }

    public Restaurant(String name, Boolean isKosher, Set<Cuisine> cuisines) {
        this.name = name;
        this.isKosher = isKosher;
        this.cuisines = cuisines;
        numberOfRates = 0;
        totalRate = 0.0;
    }


    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getKosher() {
        return isKosher;
    }

    public void setKosher(Boolean kosher) {
        isKosher = kosher;
    }

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(Set<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public Double getRating() {
        if (this.numberOfRates == 0) {
            return null;
        }
        double avg = this.totalRate / this.numberOfRates;
        return Double.parseDouble(String.format("%.2f", avg));
    }

    public Double getTotalRate() {
        return totalRate;
    }

    public void addToTotalRate(Double totalRate) {
        this.totalRate += totalRate;
    }

    public Integer getNumberOfRates() {
        return numberOfRates;
    }

    public void increaseNumberOfRates(Integer numberOfRates) {
        this.numberOfRates += numberOfRates;
    }
}
