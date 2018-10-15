package com.trimble.notepadapp.util;

/**
 * Created by mbommi on 10/10/2018.
 */

public class Billing {

    private long startedSecond;

    private static double ammount;
    private static Billing billing = new Billing();

    public static Billing getInstance(){
        return billing;
    }

    public  double getAmmount() {
        return ammount;
    }

    public void startCalculate(long millisecond){
        startedSecond = millisecond;
    }

    public void endCalculate(long millisecond){
        double hours = ((startedSecond - millisecond) / 1000) / (60 * 60);
        ammount = (float) (hours * 1);
    }

    public void cloudFeatureCalculate(int size){
        float convertToTenKB = size/10;
        ammount += convertToTenKB * 1;
    }

}
