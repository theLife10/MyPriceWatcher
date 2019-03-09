package cs4330.cs.utep.edu;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemAdapter extends ArrayAdapter<Item> {

    private Context mContext;
    private List<Item> itemList = new ArrayList<>();
    TextView itemName, currentPrice, priceChange,linkPassed;
    TextView startingPrice;
    Button b;
    Item item;
    public ItemAdapter( Context context, List<Item> items){
        super(context, 0, items);
        mContext = context;
        itemList = items;
    }


    public View getView(int position,View convertView,ViewGroup parent){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listview_row,parent,false);
            itemName = (TextView) listItem.findViewById(R.id.itemName);
            linkPassed = (TextView) listItem.findViewById(R.id.link);
            startingPrice = (TextView) listItem.findViewById(R.id.startingPrice);
            currentPrice = (TextView) listItem.findViewById(R.id.currentPrice);
            priceChange = (TextView) listItem.findViewById(R.id.percent);
            b = (Button) listItem.findViewById(R.id.popup);
        }

        item = itemList.get(position);


        itemName.setText(item.getItem());
        item.setStartPrice();
        String start =Double.toString(item.getStartPrice());
        startingPrice.setText(start);

        linkPassed.setText(item.getUrl());

        b.setOnClickListener(v -> {
            Toast.makeText(getContext(),"pressed",Toast.LENGTH_SHORT).show();
        });

       return  listItem;
    }

    public void setPercent(){
      //  item.setCurrentPrice();
      //  currentPrice.setText(Float.toString(item.getCurrentPrice()));
      //  String var  = Float.toString(item.getPercentageChange());
      //  priceChange.setText(String.format(Locale.getDefault(),"$ %.0f",var));

    }



}
