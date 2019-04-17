package cs4330.cs.utep.edu;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item>  {

    private Context mContext;
    private List<Item> itemList = new ArrayList<>();
    TextView itemName, currentPrice, priceChange;
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
            startingPrice = (TextView) listItem.findViewById(R.id.startingPrice);
            currentPrice = (TextView) listItem.findViewById(R.id.currentPrice);
            priceChange = (TextView) listItem.findViewById(R.id.percent);

        }
        item = itemList.get(position);
        itemName.setText(item.getItem());
        //Setting the starting price
        String start = Float.toString(item.getStartPrice());
        startingPrice.setText("$ "+start);

        String current = Float.toString(item.getCurrentPrice());
        currentPrice.setText("$ "+current);
        priceChange.setText("% "+Float.toString((float) item.getPercentageChange()));
       return listItem;
    }
}
