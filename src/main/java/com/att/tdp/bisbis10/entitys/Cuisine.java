package com.att.tdp.bisbis10.entitys;

public enum Cuisine {
    Asian("Asian"),
    Mexican("Mexican"),
    Indian("Indian");

    private final String name;

    Cuisine(String name) {
        this.name = name;
    }

    public static Cuisine getCuisineByString(String cuisineString) {
        for (Cuisine cuisine : Cuisine.values()) {
            if (cuisine.getName().equalsIgnoreCase(cuisineString)) {
                return cuisine;
            }
        }
        throw new IllegalArgumentException("Invalid cuisine: " + cuisineString + ". Available cuisines are: " + availableCuisines());
    }

    public static String availableCuisines() {
        StringBuilder cuisineString = new StringBuilder();
        Cuisine[] cuisines = Cuisine.values();
        for (int i = 0; i < cuisines.length; i++) {
            if (i < cuisines.length - 1) {
                cuisineString.append(cuisines[i].getName()).append(", ");
            } else {
                cuisineString.append(cuisines[i].getName());
            }

        }
        return cuisineString.toString();
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
