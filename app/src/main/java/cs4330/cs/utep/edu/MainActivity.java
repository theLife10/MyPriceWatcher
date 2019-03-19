package cs4330.cs.utep.edu;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

            return super.onOptionsItemSelected(item);

    }

    private String passLine() {
        //checking if link passed
        String action = getIntent().getAction();
        String type = getIntent().getType();
        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
            url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            //send to dialog
        }
        return url;

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
}
