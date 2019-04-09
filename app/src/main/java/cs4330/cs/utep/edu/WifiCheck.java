package cs4330.cs.utep.edu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class WifiCheck {
    int wifiState =0;

     BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                    WifiManager.WIFI_STATE_UNKNOWN);

            if(wifiStateExtra == WifiManager.WIFI_STATE_ENABLED){
                wifiState = 1;
                Toast.makeText(context,"wifi enabled", Toast.LENGTH_SHORT).show();
            }
            if(wifiStateExtra == WifiManager.WIFI_STATE_DISABLED){
                wifiState=0;
                Toast.makeText(context,"wifi disabled", Toast.LENGTH_SHORT).show();
            }
        }
    };
     public int getWifiState(){
         return wifiState;
     }
}
