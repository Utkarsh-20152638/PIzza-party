package com.example.android.pizza_party;

public class OrderPizza {
    String name;
    String quantity;

    public OrderPizza () {
    }

    public OrderPizza ( String name, String quantity ) {
        this.name=name;
        this.quantity=quantity;
    }

    public String getName () {
        return name;
    }

    public String getQuantity () {
        return quantity;
    }
}
