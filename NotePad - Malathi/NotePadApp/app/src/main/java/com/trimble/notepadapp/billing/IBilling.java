package com.trimble.notepadapp.billing;

public interface IBilling {

    double calculateCost(int   quantity, double rate);

    double calculateBill(double cost, double tax);

}
