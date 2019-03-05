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
        url = "https://www.bestbuy.com/site/hp-15-6-laptop-amd-a6-series-4gb-memory-amd-radeon-r4-1tb-hard-drive-hp-finish-in-jet-black-with-a-maglia-texture/6240847.p?skuId=6240847&ref=212&loc=1&extStoreId=829&&ref=212&loc=DWA&gclid=CjwKCAiAkrTjBRAoEiwAXpf9CdXpa-p-0dHJhsHKlwDe4ak88E_oh7RZ1hdiAvokxuCcUKe9vofArRoCQYAQAvD_BwE&gclsrc=aw.ds";
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
