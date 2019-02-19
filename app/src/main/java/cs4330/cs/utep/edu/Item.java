package cs4330.cs.utep.edu;

public class Item {


    private String mItem;
    private double mStartPrice;
    private double mCurrentPrice;
    private PriceFinder findPrice;

    public Item(){
        mItem = "Laptop";
        findPrice = new PriceFinder();
        mStartPrice = 0.00;
        mCurrentPrice = 0.00;
    }

    public double getStartPrice() {
        return mStartPrice;
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public void setStartPrice() {
        mStartPrice = findPrice.generateRandomPrices();
    }

    public void setCurrentPrice(){
        mCurrentPrice = findPrice.generateRandomPrices();
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getItem(){
        return mItem;
    }

    public double getPercentageChange(){
        return mStartPrice - mCurrentPrice;
    }

}
