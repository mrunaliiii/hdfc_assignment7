package com.chefswar.models;

import java.util.*;
import com.chefswar.enums.Role;

public class Customer extends User {
    private List<Order> history = new ArrayList<>();

    public Customer(String username) {
        super(username, Role.CUSTOMER);
    }

    public List<Order> getHistory() {
        return history;
    }

    public void addOrder(Order order) {
        history.add(order);
    }
}