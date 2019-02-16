package cs4330.cs.utep.edu;

public class Item {


    private String mItem;
    private double mStartPrice;
    private double mCurrentPrice;


    public Item(){
        mItem = "Laptop";
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getItem(){
        return mItem;
    }

    public double getPercentageChange(){
        return Math.abs(mCurrentPrice - mStartPrice);
    }

}
