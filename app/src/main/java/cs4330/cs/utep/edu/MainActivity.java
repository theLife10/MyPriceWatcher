package cs4330.cs.utep.edu;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;
import java.util.ArrayList;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    EditText diaName,diaPrice,diaUrl;
    String url;
    Item product = new Item();
    ArrayList<Item> items = new ArrayList<>();
    int requestcode = 0;
    DBHelper helper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        product.setStartPrice();

        if(savedInstanceState != null){
            items = savedInstanceState.getParcelableArrayList("items");
        }

        listview = (ListView) findViewById(R.id.listview);
        adapter = new ItemAdapter(this,items);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popup(view,position);
            }
        });

        passLine();


        helper = new DBHelper(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

            if(id == R.id.browse){
             url = "https://www.bestbuy.com";
             browse(url);
                return true;
             }

            if(id == R.id.add){
                addDialog();
                return true;
            }
            if(id == R.id.update){
                adapter.setPercent();
                listview.setAdapter(adapter);
                return true;
            }
            if(id == R.id.customtabs){
                url = "https://www.bestbuy.com";
                chromeTabs(url);
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

        diaUrl = (EditText) mView.findViewById(R.id.diaUrl);

        mBuilder.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = diaName.getText().toString();
                        String url = diaUrl.getText().toString();
                        product.setItem(name);
                        product.setUrl(url);

                        String i = product.getItem();
                        String u = product.getUrl();
                        addDataToDatabase(i,u);
                        addDataDisplay();

                        items.add(product);
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
                    Item itemUrl = items.get(position);
                    browse(itemUrl.getUrl());
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
       // adapter = new ItemAdapter(this,items);
        listview.setAdapter(adapter);

    }
    private void editDialog(int position){
        Item item = items.get(position);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog,null);

        mBuilder.setTitle("Editing Item");

        //inputs
        diaName = (EditText) mView.findViewById(R.id.diaItemName);

        diaUrl = (EditText) mView.findViewById(R.id.diaUrl);

        diaName.setText(item.getItem());

        diaUrl.setText(item.getUrl());

        mBuilder.setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = diaName.getText().toString();
                        String url = diaUrl.getText().toString();
                        item.setItem(name);
                        item.setUrl(url);
                       // adapter = new ItemAdapter(getApplicationContext(),items);
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

    public void browse(String url){
       Intent sendUrl = new Intent(getApplicationContext(),Browse.class);
       sendUrl.putExtra("url",url);

       startActivityForResult(sendUrl,requestcode);
    }
    public void chromeTabs(String url){
        Intent intent = new Intent(this, CustomBroadcastReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder()
                .setToolbarColor(Color.BLUE)
                .addMenuItem("Share via PriceWatcher",pendingIntent);
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        String returnedUrl = data.getData().toString();
        if(resultCode == 0) {
            Toast.makeText(this, returnedUrl, Toast.LENGTH_SHORT).show();
            addDialog();
            diaUrl.setText(returnedUrl);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("items", items);
    }

    public void addDataToDatabase(String name,  String url){
        float start =  product.getStartPrice();
        float curr = product.getCurrentPrice();
        double percent =  product.getPercentageChange();
        boolean in =helper.addItem(name,url,start,curr,percent);
        if(in){
            Toast.makeText(this,"passed",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show();
        }
    }

    public void addDataDisplay(){

        Cursor data = helper.getData();

        int itemID = -1;
        String itemName = null;
        String urlItem = null;
        float startPrice= (float) 0.00;
        float cur = (float) 0.00;
        float per = (float) 0.00;

        while(data.moveToNext()){
            itemID = data.getInt(0);
            itemName = data.getString(1);
            startPrice = data.getFloat(2);
            cur = data.getFloat(3);
            per = data.getFloat(4);
            urlItem = data.getString(5);

        }
        String t = Float.toString(startPrice);

        Toast.makeText(this,itemName,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,urlItem,Toast.LENGTH_SHORT).show();
        Toast.makeText(this,t,Toast.LENGTH_SHORT).show();
    }


}