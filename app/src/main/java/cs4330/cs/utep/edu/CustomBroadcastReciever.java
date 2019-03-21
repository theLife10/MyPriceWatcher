package cs4330.cs.utep.edu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.content.Intent.EXTRA_TEXT;

public class CustomBroadcastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String url = intent.getDataString();
        Toast.makeText(context,url,Toast.LENGTH_SHORT).show();

        if (url != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);

            Intent chooserIntent = Intent.createChooser(shareIntent, "Share url");
            chooserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(chooserIntent);
        }

    }
}
