package cs4330.cs.utep.edu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static android.content.Intent.EXTRA_TEXT;

public class Browse extends AppCompatActivity {
    WebView web;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        web = (WebView) findViewById(R.id.web);

        Intent getWebsite = getIntent();

        url = getWebsite.getStringExtra("url");

        web.setWebViewClient(new WebViewClient());
        web.loadUrl(url);

        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.browsemenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.Share){
           share();
            return true;
        }
        if(id == R.id.exit){
            exit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(web.canGoBack()){
            web.goBack();
        }else {
            super.onBackPressed();
        }
    }
    public void share(){
       Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        url = web.getUrl();
        shareIntent.putExtra(Intent.EXTRA_TEXT,url);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Copied url");

        shareIntent.setData(Uri.parse(url));
        setResult(0,shareIntent);
        finish();
    }
    public void exit(){
        Intent i = new Intent();
        i.setData(Uri.parse(""));
       setResult(Activity.RESULT_OK,i);
       finish();
    }
}
