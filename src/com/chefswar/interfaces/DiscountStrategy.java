package com.chefswar.interfaces;

@FunctionalInterface
public interface DiscountStrategy {
    double applyDiscount(double basePrice);
}