package com.chefswar.models;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Dish {
    private String name;
    private double price;
    private List<Integer> ratings = new ArrayList<>();

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            ratings.add(rating);
        }
    }

    public double avgRating() {
        OptionalDouble avg = ratings.stream().mapToInt(i -> i).average();
        return avg.orElse(0);
    }
}