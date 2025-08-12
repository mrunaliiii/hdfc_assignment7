package com.chefswar.models;

import com.chefswar.interfaces.Observer;
import com.chefswar.interfaces.DiscountStrategy;

import java.util.*;
import java.util.stream.Collectors;

public class Restaurant {
    private String name;
    private List<Dish> dishes = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private DiscountStrategy currentDiscount = price -> price; // default no discount
    private boolean blocked = false;
    private double revenue = 0;
    private List<Observer> competitors = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public double getRevenue() {
        return revenue;
    }

    public void registerCompetitor(Observer observer) {
        competitors.add(observer);
    }

    public void setCurrentDiscount(DiscountStrategy discount) {
        this.currentDiscount = discount;
    }

    public DiscountStrategy getCurrentDiscount() {
        return currentDiscount;
    }

    public void updatePrice(String dishName, double newPrice) {
        Optional<Dish> dishOpt = dishes.stream()
                .filter(d -> d.getName().equalsIgnoreCase(dishName))
                .findFirst();

        dishOpt.ifPresent(dish -> {
            double oldPrice = dish.getPrice();
            double drop = (oldPrice - newPrice) / oldPrice;
            dish.setPrice(newPrice);
            if (drop > 0.15) {
                // Notify competitors
                competitors.forEach(obs -> obs.priceDropped(dishName, newPrice));
            }
        });
    }

    public void processOrder(Order order) {
        orders.add(order);
        revenue += order.calculateTotal();
    }

    @Override
    public String toString() {
        return name + (blocked ? " [BLOCKED]" : "");
    }
}