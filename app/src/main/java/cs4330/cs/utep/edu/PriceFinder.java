package cs4330.cs.utep.edu;

import java.util.Random;

public class PriceFinder {
    private String url;
    public float findPrice(){
        double price = 0.00;
        Random r = new Random();
        price = (r.nextInt((int)((500-50)*10+1))+50*10) / 10.0;

        float floatingnumber = (float) price;
        return floatingnumber;
    }
}
