package cs4330.cs.utep.edu;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    TextView linkPassed;
    String url;
    ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        adapter = new ItemAdapter(this, items);
        listview.setAdapter(adapter);
            if(id == R.id.add){
                items.add(new Item());

                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                return true;
            }
            if(id == R.id.update){
                Toast.makeText(this,"update",Toast.LENGTH_SHORT).show();
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
            linkPassed.setText(url);
        }

    }
}
