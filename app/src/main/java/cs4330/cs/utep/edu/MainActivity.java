package cs4330.cs.utep.edu;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listview;
    ItemAdapter adapter;
   // Button calButton;
   // private ListView listview;

    //Item item = new Item();
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

        //passLine();

        adapter = new ItemAdapter(this,items);
        listview.setAdapter(adapter);

    }

   // private void passLine() {
        //checking if link passed
       // linkPassed = (TextView) findViewById(R.id.link);
     //   url= "https://www.bestbuy.com/site/hp-15-6-laptop-amd-a6-series-4gb-memory-amd-radeon-r4-1tb-hard-drive-hp-finish-in-jet-black-with-a-maglia-texture/6240847.p?skuId=6240847&ref=212&loc=1&extStoreId=829&&ref=212&loc=DWA&gclid=CjwKCAiAkrTjBRAoEiwAXpf9CdXpa-p-0dHJhsHKlwDe4ak88E_oh7RZ1hdiAvokxuCcUKe9vofArRoCQYAQAvD_BwE&gclsrc=aw.ds";
       // linkPassed.setText(url);
       // String action = getIntent().getAction();
        //String type =getIntent().getType();
        //if(Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
          //  url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
           // linkPassed.setText(url);
        //}
    //}
}
