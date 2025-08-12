package com.chefswar.models;

import java.util.List;

public class CustomizedDish {
    private Dish baseDish;
    private List<Addon> addons;

    public CustomizedDish(Dish baseDish, List<Addon> addons) {
        this.baseDish = baseDish;
        this.addons = addons;
    }

    public double price() {
        return baseDish.getPrice() + addons.stream().mapToDouble(Addon::getPrice).sum();
    }

    public Dish getBaseDish() {
        return baseDish;
    }

    public List<Addon> getAddons() {
        return addons;
    }
}