package cs4330.cs.utep.edu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;


import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private List<Item> itemList = new ArrayList<>();
    private Item item;
    TextView itemName;
    public ItemAdapter( Context context, List<Item> items){
        super(context, 0, items);
        mContext = context;
        itemList = items;
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent){
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_row,parent,false);

        //setting item name
        itemName = (TextView) listItem.findViewById(R.id.itemName);
        itemName.setText(item.getItem());

        //init  startingPrice, currentPrice, and priceChange
       TextView startingPrice = (TextView) listItem.findViewById(R.id.startingPrice);
       TextView currentPrice = (TextView) listItem.findViewById(R.id.currentPrice);
       TextView priceChange = (TextView) listItem.findViewById(R.id.percent);
       return  convertView;
    }
}
