package com.chefswar.models;

import com.chefswar.enums.OrderStatus;
import com.chefswar.interfaces.DiscountStrategy;

import java.util.List;

public class Order {
    private Customer customer;
    private Restaurant restaurant;
    private Dish dish;
    private List<Addon> addons;
    private DiscountStrategy discountStrategy;
    private OrderStatus status;

    public Order(Customer customer, Restaurant restaurant, Dish dish, List<Addon> addons, DiscountStrategy discountStrategy) {
        this.customer = customer;
        this.restaurant = restaurant;
        this.dish = dish;
        this.addons = addons;
        this.discountStrategy = discountStrategy;
        this.status = OrderStatus.PLACED;
    }

    public Dish getDish() {
        return dish;
    }

    public double calculateTotal() {
        double basePrice = dish.getPrice();
        double addonCost = addons.stream().mapToDouble(Addon::getPrice).sum();
        double discounted = discountStrategy.applyDiscount(basePrice + addonCost);
        double tax = discounted * 0.05; // GST 5%
        return discounted + tax;
    }

    public String printBill() {
        double basePrice = dish.getPrice();
        double addonCost = addons.stream().mapToDouble(Addon::getPrice).sum();
        double discounted = discountStrategy.applyDiscount(basePrice + addonCost);
        double tax = discounted * 0.05;

        return String.format("Bill:\nBase price: %.2f\nAdd-ons: %.2f\nDiscounted price: %.2f\nGST (5%%): %.2f\nTotal: %.2f",
                basePrice, addonCost, discounted, tax, discounted + tax);
    }
}