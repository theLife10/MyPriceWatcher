package cs4330.cs.utep.edu;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
    TextView linkPassed;
   // Button calButton;

    Item item = new Item();
    String url ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Item> items = new ArrayList<>();

        listview = (ListView) findViewById(R.id.listview);

        items.add(new Item());
        items.add(new Item());
        //calButton = (Button) findViewById(R.id.cal);


//        calButton.setOnClickListener(v -> {
            //settingNumbers();
  //      });



        adapter = new ItemAdapter(this,items);
        listview.setAdapter(adapter);

    }

    private void passLine(View listItem) {
        //checking if link passed
        String action = getIntent().getAction();
        String type =getIntent().getType();
        if(Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
            url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            linkPassed.setText(url);
        }
    }


}
