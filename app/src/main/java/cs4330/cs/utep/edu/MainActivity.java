package cs4330.cs.utep.edu;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView itemName;
    TextView currentPrice;
    TextView startingPrice;
    TextView priceChange;
    TextView linkPassed;
    Button calButton;
    Button launch;
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
        launch = (Button) findViewById(R.id.launch);

        item.setStartPrice();
        double start = item.getStartPrice();
        startingPrice.setText(Double.toString(start));

        calButton.setOnClickListener(v -> {
            settingNumbers();
        });

        launch.setOnClickListener(v -> {
            launchWebsite(url);
        });

        //checking if link passed

        linkPassed = (TextView) findViewById(R.id.link);

        String action = getIntent().getAction();
        String type =getIntent().getType();
        if(Intent.ACTION_SEND.equalsIgnoreCase(action) && type != null && ("text/plain".equals(type))) {
            url = getIntent().getStringExtra(Intent.EXTRA_TEXT);
            linkPassed.setText(url);
        }
    }

    public void settingNumbers(){
        item.setCurrentPrice();
        currentPrice.setText(Double.toString(item.getCurrentPrice()));
        priceChange.setText(Double.toString(item.getPercentageChange()));
    }

    public void launchWebsite(String url){

        Uri website = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, website);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
