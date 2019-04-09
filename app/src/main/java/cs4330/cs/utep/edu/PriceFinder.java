package cs4330.cs.utep.edu;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class PriceFinder  {

    public float findPrice(){
        float price = (float) 0.00;
        Random r = new Random();
        price = (float) ((r.nextInt((int)((500-50)*10+1))+50*10) / 10.0);

        float floatingnumber = (float) price;
        return floatingnumber;
    }


    protected Void doInBackground(Void... voids) {
      //  try {
            //Connect to the website
          //  Document document = Jsoup.connect(url).get();

            //Get the logo source of the website
           // Element img = document.select("img").first();
            // Locate the src attribute
          //  String imgSrc = img.absUrl("src");
            // Download image from URL
          //  InputStream input = new java.net.URL(imgSrc).openStream();
            //

            //Get the title of the website
        //    String title = document.title();

   //     } catch (IOException e) {
            //e.printStackTrace();
    //    }

        return null;
    }
   // @Override
    protected void onPostExecute(Void voids){

    }
}
