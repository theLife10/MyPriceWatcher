package cs4330.cs.utep.edu;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

public class Item implements Parcelable {


    private String mItem;
    private float mStartPrice;
    private float mCurrentPrice;
    private String mUrl;
    private int mId;


    public Item(){
        mItem = "";
        mStartPrice = (float) 0.00;
        mCurrentPrice = (float) 0.00;
    }
    public Item(int id,String item, String url,float start, float current) {
        mId = id;
        mItem = item;
        mUrl = url;
        mStartPrice=start;
        mCurrentPrice = current;
    }

    protected Item(Parcel in) {
        mItem = in.readString();
        mStartPrice = in.readFloat();
        mCurrentPrice = in.readFloat();
        mUrl = in.readString();
        mId = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public void setId(int i){
        mId =i;
    }
    public int getId(){
        return mId;
    }

    public float getStartPrice() {
        return mStartPrice;
    }

    public float getCurrentPrice() {
        return mCurrentPrice;
    }

    public void setStartPrice(String price) {
        mStartPrice = Float.parseFloat(price);
    }

    public void setCurrentPrice(String p){
        mCurrentPrice = Float.parseFloat(p);;
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

    public void setUrl(String surl){
        mUrl=surl;
    }

    public double getPercentageChange(){
        return (mStartPrice - mCurrentPrice) / 100;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mItem);
        dest.writeFloat(mStartPrice);
        dest.writeFloat(mCurrentPrice);
        dest.writeString(mUrl);
        dest.writeInt(mId);

    }
}
