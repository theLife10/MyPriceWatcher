package cs4330.cs.utep.edu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, int resource, List<Item> items){
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        return  convertView;
    }
}
