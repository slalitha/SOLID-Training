package com.trimble.notepadapp.billing;

/**
 * Created by mbommi on 10/11/2018.
 */

public abstract class IBillingWithOffer extends BaseBilling {

    private static final double percent = 0.2;

    public double applyOffer(double cost) {
        return cost - cost * percent;
    }


}
