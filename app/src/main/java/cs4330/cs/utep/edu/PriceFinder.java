package cs4330.cs.utep.edu;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class PriceFinder extends AsyncTask<String,Void,String> {
    private String cssQuery = null;
    private String dollar;
    private Execute executeStoreFilter;
    private String cents = null;
    @Override
    protected String doInBackground(String...url) {
        try {
            String[] split = url[0].trim().split("\\.");

            if(split[1].equals("walmart")){
                executeStoreFilter = new Execute(new Walmart());
                Document document = Jsoup.connect(url[0]).get();
                cssQuery = document.select("span[class=display-inline-block-xs prod-PaddingRight--xs valign-top]").text();
                dollar = executeStoreFilter.execute(cssQuery);
            }
            if(split[1].equals("samsclub")){
                executeStoreFilter = new Execute(new Samsclub());
                Document document = Jsoup.connect(url[0]).get();
                cssQuery = document.select("span[class=Price-group]").text();
                dollar = executeStoreFilter.execute(cssQuery);
            }
            if(split[1].equals("homedepot")){
               String extractCents =null;
                executeStoreFilter = new Execute(new Homedepot());
                Document document = Jsoup.connect(url[0]).cookie("_mibhv","anon-1554851458201-9035811486_4577").get();
                cssQuery = document.select("span[class=price__dollars]").text();
                extractCents = document.select("span[class=price__cents]").text();
                dollar = executeStoreFilter.execute(cssQuery);
                cents = executeStoreFilter.execute(extractCents);
                return dollar+"."+cents;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return dollar;
    }
}
