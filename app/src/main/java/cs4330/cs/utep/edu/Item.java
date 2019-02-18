package cs4330.cs.utep.edu;

public class Item {


    private String mItem;
    private double mStartPrice;
    private double mCurrentPrice;


    public double getStartPrice() {
        return mStartPrice;
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public void updatePrice(){
        mCurrentPrice = mCurrentPrice + 5;
    }


    public Item(){
        mItem = "Laptop";
        mCurrentPrice = 100.00;
        mStartPrice = 100.00;
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getItem(){
        return mItem;
    }



    public double getPercentageChange(){
        return mCurrentPrice - mStartPrice;
    }

}
