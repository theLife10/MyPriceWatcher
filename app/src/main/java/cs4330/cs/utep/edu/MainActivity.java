package cs4330.cs.utep.edu;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    EditText diaName,diaPrice,diaUrl;
    String url;
    Item product;
    List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        adapter = new ItemAdapter(this,items);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popup(view,position);
            }
        });

        //passLine();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

            if(id == R.id.add){
                addDialog();
                return true;
            }
            if(id == R.id.update){
                adapter.setPercent();
                adapter.notifyDataSetChanged();
               // Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
                return true;
            }
            if(id == R.id.browse){
                browse();
                return true;
            }

            return super.onOptionsItemSelected(item);
    }

    public void passLine() {
        //checking if link passed
        String action = getIntent().getAction();
        String type = getIntent().getType();
        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
            url = getIntent().getStringExtra(EXTRA_TEXT);
            //send to dialog
            addDialog();
            diaUrl.setText(url);

        }
    }
    private void addDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog,null);

        mBuilder.setTitle("Adding Item");

        //inputs
        diaName = (EditText) mView.findViewById(R.id.diaItemName);
        diaPrice =(EditText) mView.findViewById(R.id.diaStartingPrice);
        diaUrl = (EditText) mView.findViewById(R.id.diaUrl);

        mBuilder.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = diaName.getText().toString();
                        String startPrice = diaPrice.getText().toString();
                        float dstartprice = Float.parseFloat(startPrice);
                        String url = diaUrl.getText().toString();
                        items.add(new Item(name,dstartprice,url));
                      //  adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();

    }
    private void popup(View v, int position){
        PopupMenu popup = new PopupMenu(this,v);
        popup.inflate(R.menu.popup);
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.edit){
                    // createDialog();
                    editDialog(position);
                }
                if(item.getItemId() == R.id.browse){
                    browse();
                    return true;
                }
                if(item.getItemId() == R.id.remove){
                    removeItem(position);
                    return true;
                }

                return false;
            }
        });
    }
    private void removeItem(int position){
        Item item = items.get(position);
        items.remove(item);
        adapter = new ItemAdapter(this,items);
        listview.setAdapter(adapter);

    }
    private void editDialog(int position){
        Item item = items.get(position);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog,null);

        mBuilder.setTitle("Editing Item");

        //inputs
        diaName = (EditText) mView.findViewById(R.id.diaItemName);
        diaPrice =(EditText) mView.findViewById(R.id.diaStartingPrice);
        diaUrl = (EditText) mView.findViewById(R.id.diaUrl);

        diaName.setText(item.getItem());
        String startPrice = Float.toString((float) item.getStartPrice());
        diaPrice.setText(startPrice);
        diaUrl.setText(item.getUrl());

        mBuilder.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = diaName.getText().toString();
                        String startPrice = diaPrice.getText().toString();
                        float dstartprice = Float.parseFloat(startPrice);
                        String url = diaUrl.getText().toString();
                        item.setItem(name);
                        item.setStartPrice(dstartprice);
                        item.setUrl(url);
                        adapter = new ItemAdapter(getApplicationContext(),items);
                        listview.setAdapter(adapter);
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
    public void browse(){
        url = "https://www.bestbuy.com";
        Intent intent = new Intent(this, CustomBroadcastReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder()
                .setToolbarColor(Color.BLUE)
                .addMenuItem("Share via PriceWatcher",pendingIntent);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));

    }
}