package cs4330.cs.utep.edu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView itemText;
    Button calButton;
    Item item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemText = (TextView) findViewById(R.id.itemName);
        itemText.setText("hello");


        //calButton.setOnClickListener((v) -> {

        //});
    }
}
