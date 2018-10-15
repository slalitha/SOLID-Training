package com.trimble.notepadapp.billing;

/**
 * Created by mbommi on 10/10/2018.
 */

public class BillingFactory  {


    public  static IBilling getInstance(String type){
        IBilling billing = null;
        if(type.equals("Subscription")){
            billing = new BillingSubscription();
        }
        else if(type.equals("Donation")) {
            billing = new BillingDonation();
        }
        else if(type.equals("Time")) {
            billing = new BillingFeatureBased();
        }
        else if(type.equals("Feature")) {
            billing = new BillingTimeBased();
        }

        return billing;
    }
}
