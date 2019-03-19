package cs4330.cs.utep.edu;
import android.app.PendingIntent;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.EXTRA_TEXT;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    EditText diaName,diaPrice,diaUrl;
    Button diaSave,diaCancel;
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

        passLine();
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
              //  adapter.setPercent();
               // adapter.notifyDataSetChanged();
              //  Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
                return true;
            }
            if(id == R.id.browse){
                browse();
                return true;
            }

            return super.onOptionsItemSelected(item);

    }

    private void passLine() {
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
                        adapter.notifyDataSetChanged();
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
    private void browse(){
        String url = "https://www.bestbuy.com";
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder()
                .setToolbarColor(Color.BLUE);
        //
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.getStringExtra(EXTRA_TEXT);
        PendingIntent menuIntent = PendingIntent.getActivity(this, 0, shareIntent, 0);
        builder.addMenuItem("Share via PriceWatcher", menuIntent);

        //







        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));



    }
}
