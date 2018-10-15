package com.trimble.notepadapp.billing;

/**
 * Created by mbommi on 10/11/2018.
 */

public class BaseBilling implements IBilling {
    @Override
    public double calculateCost(int quantity, double rate) {
        return quantity * rate;
    }

    @Override
    public double calculateBill(double cost, double tax) {
        return cost + cost * tax;
    }
}
