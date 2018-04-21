package com.example.pulkit.conveyor;

/**
 * Created by pulkit on 4/14/2018.
 */

public class Conveyor {


    private String mBarcode;
    private String mNumber;

    public Conveyor(String barcode, String number){
        mBarcode=barcode;
        mNumber=number;
    }

    public String getNumber(){
        return mNumber;
    }

    public String getBarcode(){
        return mBarcode;
    }
}
