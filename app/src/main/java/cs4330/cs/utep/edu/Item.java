package cs4330.cs.utep.edu;

public class Item {


    private String mItem;
    private float mStartPrice;
    private float mCurrentPrice;
    private PriceFinder findPrice;
    private String mUrl;


    public Item(){
        mItem = "";
        findPrice = new PriceFinder();
        mStartPrice = (float) 0.00;
        mCurrentPrice = (float) 0.00;
    }
    public Item(String item, float startPrice,String url) {
        mItem = item;
        mStartPrice = startPrice;
        mUrl = url;
        findPrice = new PriceFinder();
    }

    public double getStartPrice() {
        return mStartPrice;
    }

    public double getCurrentPrice() {
        return mCurrentPrice;
    }

    public void setStartPrice(float start) {
        mStartPrice = start;
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
        return mUrl;
    }

    public double getPercentageChange(){
        return (mStartPrice - mCurrentPrice) / 100;
    }

}
