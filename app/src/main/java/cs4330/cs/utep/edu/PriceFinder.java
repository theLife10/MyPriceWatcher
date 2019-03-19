package cs4330.cs.utep.edu;

import java.util.Random;

public class PriceFinder {

    public float findPrice(){
        float price = (float) 0.00;
        Random r = new Random();
        price = (float) ((r.nextInt((int)((500-50)*10+1))+50*10) / 10.0);

        float floatingnumber = (float) price;
        return floatingnumber;
    }
}
