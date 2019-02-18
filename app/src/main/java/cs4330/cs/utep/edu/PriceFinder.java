package cs4330.cs.utep.edu;

import java.util.Random;

public class PriceFinder {
    private double price;

    public PriceFinder(){
        price = 0.00;
    }

    public double generateRandomPrices(){
        Random r = new Random();
        price = (r.nextInt((int)((500-50)*10+1))+50*10) / 10.0;
        return price;
    }


}
