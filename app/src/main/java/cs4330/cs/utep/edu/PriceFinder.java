package cs4330.cs.utep.edu;
import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class PriceFinder extends AsyncTask<String,Void,String> {
    private String i = null;
    private String w;
    private Execute e;
    private String cents = null;
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
               String extractCents =null;
                e = new Execute(new Homedepot());
                Document document = Jsoup.connect(url[0]).cookie("_mibhv","anon-1554851458201-9035811486_4577").get();
                i = document.select("span[class=price__dollars]").text();
                extractCents = document.select("span[class=price__cents]").text();
                w = e.execute(i);
                cents = e.execute(extractCents);
                return w+"."+cents;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return w;
    }
}
