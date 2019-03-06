package cs4330.cs.utep.edu;

public class Item {


    private String mItem;
    private float mStartPrice;
    private float mCurrentPrice;
    private PriceFinder findPrice;
    private String url;

    public Item(){
        mItem = "Laptop";
        findPrice = new PriceFinder();
        mStartPrice = (float) 0.00;
        mCurrentPrice = (float) 0.00;
        url = "www.google.com";
    }

    public float getStartPrice() {
        return mStartPrice;
    }

    public float getCurrentPrice() {
        return mCurrentPrice;
    }

    public void setStartPrice() {
        mStartPrice = findPrice.findPrice();
    }

    public void setCurrentPrice(){
        mCurrentPrice = findPrice.findPrice();
    }

    public void setItem(String item) {
        mItem = item;
    }

    public String getItem(){
        return mItem;
    }

    public String getUrl(){
        return url;
    }

    public float getPercentageChange(){
        return mStartPrice - mCurrentPrice;
    }

}
