package cs4330.cs.utep.edu;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.net.wifi.WifiManager;
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
import java.util.concurrent.ExecutionException;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    EditText diaName,diaPrice,diaUrl;
    String url;
    Item product = new Item();
    ArrayList<Item> items ;
    int requestcode = 0;
    DBHelper helper = new DBHelper(this);
    WifiManager wifiManager;
    WifiCheck check = new WifiCheck();
    String pr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        helper = new DBHelper(this);
        items = (ArrayList<Item>) helper.allItems();

       // product.setStartPrice();

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
       // check = new WifiCheck();
      //  check.checkForWifi();
        passLine();


       // new Price().execute("https://www.homedepot.com/");


    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intent = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(check.wifiReceiver,intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

            if(id == R.id.sams){
             url = "https://www.samsclub.com";
             browse(url);
                return true;
             }
             if(id == R.id.walmart){
                 url = "https://www.walmart.com";
                 browse(url);
                 return true;
             }
             if(id == R.id.homedepot){
                     url = "https://www.homedepot.com";
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
                        up(product,1);

                        product.setStartPrice(pr);

                        addDataToDatabase(i,u);
                        //addDataDisplay();

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
                if(item.getItemId() == R.id.update){
                        Item it = items.get(position);
                        up(it,0);
                }

                return false;
            }
        });
    }
    private void removeItem(int position){
        Item item = items.get(position);
        int id = item.getId();
        String sId = Integer.toString(id);

        helper.deleteData(sId);
        items.remove(item);

        adapter.notifyDataSetChanged();
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
                      //  helper.update(item);
                      //  adapter.notifyDataSetChanged();
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
       // String sStart= Float.toString(start);
       // Toast.makeText(this,start,Toast.LENGTH_SHORT).show();
        float curr = product.getCurrentPrice();
       // double percent =  product.getPercentageChange();
        boolean in =helper.addItem(name,url,start,curr);
        String s =Float.toString(product.getStartPrice());
        if(in){
            Toast.makeText(this,"hi",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show();
        }
    }
    public void up(Item it,int i){
        PriceFinder f =new PriceFinder();
        if(i ==0){
            String url = it.getUrl();
            Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
            f.execute(url);
            try {
                pr =f.get();
                it.setCurrentPrice(pr);
                String id = Integer.toString(it.getId());
                float curr =it.getCurrentPrice();
                Toast.makeText(getApplicationContext(), pr, Toast.LENGTH_SHORT).show();
                helper.update(curr,id);
                adapter.notifyDataSetChanged();
                listview.setAdapter(adapter);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }else {
            String u = it.getUrl();
            f.execute(u);
            try {
                pr =f.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}