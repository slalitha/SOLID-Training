package com.trimble.notepadapp.billing;

/**
 * Created by mbommi on 10/10/2018.
 */

public class Main {

    public static void main(String args[]){
       System.out.print("Taola cost  " + calculateBill());
    }
    public static double calculateBill() {

        double cost ;
        IBilling subscription = BillingFactory.getInstance("Subscription");
        cost = subscription.calculateBill(100,0);

        IBilling donation = BillingFactory.getInstance("Donation");
        cost += donation.calculateBill(100,0);

        IBilling time = BillingFactory.getInstance("Time");
        cost += time.calculateBill(time.calculateCost(2,1),0.2);

        IBilling feature = BillingFactory.getInstance("Feature");
        cost += feature.calculateBill(feature.calculateCost(5,2.1),0.4);

        return cost;
    }
}
