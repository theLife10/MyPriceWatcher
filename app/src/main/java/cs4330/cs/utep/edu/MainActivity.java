package cs4330.cs.utep.edu;

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
    Button calButton;
    Item item = new Item();
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

        double start = item.getStartPrice();
        startingPrice.setText(Double.toString(start));

        calButton.setOnClickListener(v -> {
            settingNumbers();
        });
    }

    public void settingNumbers(){
        item.updatePrice();
        currentPrice.setText(Double.toString(item.getCurrentPrice()));
        priceChange.setText(Double.toString(item.getPercentageChange()));
    }
}
