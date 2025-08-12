package com.chefswar.models;

import com.chefswar.enums.Role;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SystemAdmin extends User {

    public SystemAdmin(String username) {
        super(username, Role.SYSTEM_ADMIN);
    }

    public void blockIfNeeded(Restaurant restaurant) {
        if (restaurant.getDishes().size() < 5) {
            restaurant.setBlocked(true);
            System.out.println("Restaurant " + restaurant.getName() + " blocked due to insufficient menu size.");
        }
    }

    public void unblockIfPossible(Restaurant restaurant) {
        if (restaurant.getDishes().size() >= 5) {
            restaurant.setBlocked(false);
            System.out.println("Restaurant " + restaurant.getName() + " unblocked.");
        }
    }

    public void viewAnalytics(List<Restaurant> restaurants) {
        System.out.println("Top 3 most ordered dishes overall:");
        restaurants.stream()
                .flatMap(r -> r.getOrders().stream())
                .collect(Collectors.groupingBy(o -> o.getDish().getName(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .forEach(e -> System.out.println("Dish: " + e.getKey() + ", Orders: " + e.getValue()));

        restaurants.stream()
                .max((r1, r2) -> Double.compare(r1.getRevenue(), r2.getRevenue()))
                .ifPresent(r -> System.out.println("Restaurant with highest revenue: " + r.getName() + " with revenue " + r.getRevenue()));

        restaurants.stream()
                .flatMap(r -> r.getDishes().stream())
                .filter(d -> !d.getRatings().isEmpty())
                .max((d1, d2) -> Double.compare(d1.avgRating(), d2.avgRating()))
                .ifPresent(d -> System.out.println("Dish with highest average rating: " + d.getName() + " (" + d.avgRating() + ")"));
    }
}