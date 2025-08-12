package com.chefswar.models;

import com.chefswar.enums.Role;
import com.chefswar.interfaces.Observer;

public class RestaurantOwner extends User implements Observer {

    public RestaurantOwner(String username) {
        super(username, Role.RESTAURANT_OWNER);
    }

    @Override
    public void priceDropped(String dishName, double newPrice) {
        System.out.println("Owner " + username + " noticed price drop for " + dishName + " to " + newPrice + " and is reacting accordingly.");
    }
}