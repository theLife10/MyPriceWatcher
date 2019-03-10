package cs4330.cs.utep.edu;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class ItemAdapter extends ArrayAdapter<Item> implements PopupMenu.OnMenuItemClickListener {

    private Context mContext;
    private List<Item> itemList = new ArrayList<>();
    TextView itemName, currentPrice, priceChange,linkPassed;
    TextView startingPrice;

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
        }

        item = itemList.get(position);


        itemName.setText(item.getItem());
        item.setStartPrice();
        String start =Double.toString(item.getStartPrice());
        startingPrice.setText(start);

        linkPassed.setText(item.getUrl());

        listItem.setOnClickListener(v -> {
            popup(v);
        });

       return  listItem;
    }

    private void popup(View v) {
        PopupMenu popup = new PopupMenu(getContext(),v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup);
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item){
        return false;
    }

    public void setPercent(){
      //  item.setCurrentPrice();
      //  currentPrice.setText(Float.toString(item.getCurrentPrice()));
      //  String var  = Float.toString(item.getPercentageChange());
      //  priceChange.setText(String.format(Locale.getDefault(),"$ %.0f",var));

    }



}
