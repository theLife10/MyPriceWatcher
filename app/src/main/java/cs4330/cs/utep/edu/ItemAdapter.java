package cs4330.cs.utep.edu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private List<Item> itemList = new ArrayList<>();
    TextView itemName, currentPrice, startingPrice,priceChange;
    public ItemAdapter( Context context, List<Item> items){
        super(context, 0, items);
        mContext = context;
        itemList = items;
    }
    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_row,parent,false);
        }

        Item item = itemList.get(position);
        item.setStartPrice();

        //setting item name
        itemName = (TextView) listItem.findViewById(R.id.itemName);
        itemName.setText(item.getItem());

        startingPrice = (TextView) listItem.findViewById(R.id.startingPrice);
        startingPrice.setText(Double.toString(item.getStartPrice()));

        currentPrice = (TextView) listItem.findViewById(R.id.currentPrice);
        currentPrice.setText(Double.toString(item.getCurrentPrice()));

        //init  startingPrice, currentPrice, and priceChange


       // priceChange = (TextView) listItem.findViewById(R.id.percent);


       // settingNumbers();
       return  listItem;
    }

   // public void settingNumbers(){
      //  item.setCurrentPrice();
//        format the strings
        //currentPrice.setText(Float.toString(item.getCurrentPrice()));
      //  priceChange.setText(Float.toString(item.getPercentageChange()));
   // }
}
