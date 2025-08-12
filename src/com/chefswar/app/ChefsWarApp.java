package com.chefswar.app;

import com.chefswar.models.*;
import com.chefswar.enums.*;
import com.chefswar.interfaces.DiscountStrategy;

import java.util.Arrays;
import java.util.List;

public class ChefsWarApp {
    public static void main(String[] args) {
        Restaurant r1 = new Restaurant("Pasta Palace");
        Restaurant r2 = new Restaurant("Curry Corner");

        // Register competitors (observer pattern)
        r1.registerCompetitor(new RestaurantOwner("owner2")::priceDropped);
        r2.registerCompetitor(new RestaurantOwner("owner1")::priceDropped);

        // Add dishes to r1
        Arrays.asList(
                new Dish("Spaghetti", 200),
                new Dish("Lasagna", 250),
                new Dish("Ravioli", 220),
                new Dish("Fettuccine", 230),
                new Dish("Penne", 210)
        ).forEach(r1::addDish);

        // Add dishes to r2
        Arrays.asList(
                new Dish("Chicken Curry", 300),
                new Dish("Paneer Butter Masala", 280),
                new Dish("Naan", 50),
                new Dish("Biryani", 320),
                new Dish("Samosa", 40)
        ).forEach(r2::addDish);

        Customer cust = new Customer("guest");
        SystemAdmin admin = new SystemAdmin("admin");

        // Simulate a big price drop on Chicken Curry by r2 (20% drop triggers observer)
        r2.updatePrice("Chicken Curry", 240);

        // Customer places order on r2 with addon
        Addon extraSpice = new Addon("Extra Spice", 10);
        DiscountStrategy discount = price -> price * 0.9;  // 10% off

        Order order = new Order(cust, r2, r2.getDishes().get(0), Arrays.asList(extraSpice), discount);
        r2.processOrder(order);
        cust.addOrder(order);

        System.out.println(order.printBill());

        // Admin analytics
        admin.viewAnalytics(Arrays.asList(r1, r2));
    }
}