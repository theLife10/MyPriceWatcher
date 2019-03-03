package cs4330.cs.utep.edu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView itemName,currentPrice,startingPrice,priceChange,linkPassed;
    Button calButton;
    Item item = new Item();
    String url ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting item name
        itemName = (TextView) findViewById(R.id.itemName);
        itemName.setText(item.getItem());

        //init  startingPrice, currentPrice, and priceChange
        startingPrice = (TextView) findViewById(R.id.startingPrice);
        currentPrice = (TextView) findViewById(R.id.currentPrice);
        priceChange = (TextView) findViewById(R.id.percent);


        calButton = (Button) findViewById(R.id.cal);


        item.setStartPrice();
        double start = item.getStartPrice();
        startingPrice.setText(Double.toString(start));

        calButton.setOnClickListener(v -> {
            settingNumbers();
        });

        passLine();

    }

    private void passLine() {
        //checking if link passed
        linkPassed = (TextView) findViewById(R.id.link);
        url= "https://www.bestbuy.com/site/hp-15-6-laptop-amd-a6-series-4gb-memory-amd-radeon-r4-1tb-hard-drive-hp-finish-in-jet-black-with-a-maglia-texture/6240847.p?skuId=6240847&ref=212&loc=1&extStoreId=829&&ref=212&loc=DWA&gclid=CjwKCAiAkrTjBRAoEiwAXpf9CdXpa-p-0dHJhsHKlwDe4ak88E_oh7RZ1hdiAvokxuCcUKe9vofArRoCQYAQAvD_BwE&gclsrc=aw.ds";
        linkPassed.setText(url);
        String action = getIntent().getAction();
        String type =getIntent().getType();
        if(Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
            url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            linkPassed.setText(url);
        }
    }

    public void settingNumbers(){
        item.setCurrentPrice();
        //format the strings
        currentPrice.setText(Float.toString(item.getCurrentPrice()));
        priceChange.setText(Float.toString(item.getPercentageChange()));
    }
}
