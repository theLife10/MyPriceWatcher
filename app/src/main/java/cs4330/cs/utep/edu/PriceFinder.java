package cs4330.cs.utep.edu;

public class PriceFinder {
    private String mUrl;

    public PriceFinder(){
        mUrl = "https://www.bestbuy.com/site/dell-23-8-touch-screen-all-in-one-amd-a9-series-8gb-memory-1tb-hard-drive-black/6196009.p?skuId=6196009";
    }

    public void setUrl(String url){
        this.mUrl = url;
    }
    public String getUrl(){
        return mUrl;
    }
}
