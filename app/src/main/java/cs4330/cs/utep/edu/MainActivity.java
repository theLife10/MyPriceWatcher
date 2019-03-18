package cs4330.cs.utep.edu;


import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    TextView linkPassed;
    EditText diaName,diaPrice,diaUrl;
    String url;
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
                //items.add(new Item());
                //adapter.notifyDataSetChanged();
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

    private void passLine() {
        //checking if link passed
        String action = getIntent().getAction();
        String type = getIntent().getType();
        if (Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
            url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            //send to dialog
            linkPassed.setText(url);
        }

    }
    private void addDialog(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.layout_dialog,null);

        diaName = (EditText) mView.findViewById(R.id.diaItemName);
        diaPrice =(EditText) mView.findViewById(R.id.diaStartingPrice);
        diaUrl = (EditText) mView.findViewById(R.id.diaUrl);



        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }
}
