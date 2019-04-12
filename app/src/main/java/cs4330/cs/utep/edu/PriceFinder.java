package cs4330.cs.utep.edu;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class PriceFinder extends AsyncTask<String,Void,String> {
    String mUrl= null;
    String i = null;
    String w;
    Execute e;

    @Override
    protected String doInBackground(String...url) {
        try {
            String[] split = url[0].trim().split("\\.");

            if(split[1].equals("walmart")){
                e = new Execute(new Walmart());
                Document document = Jsoup.connect(url[0]).get();
                i = document.select("span[class=display-inline-block-xs prod-PaddingRight--xs valign-top]").text();
                w = e.execute(i);
            }
            if(split[1].equals("samsclub")){
                e = new Execute(new Samsclub());
                Document document = Jsoup.connect(url[0]).get();
                i = document.select("span[class=Price-group]").text();
                w = e.execute(i);
            }
            if(split[1].equals("homedepot")){

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return w;
    }



}
