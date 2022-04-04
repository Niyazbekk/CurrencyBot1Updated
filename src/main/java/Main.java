import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    public static void main(String[] args){
        new Bot().server();
    }

    public static String getCurr(String currCode) throws IOException {
        Document doc = Jsoup.connect(currCode).get();
        StringBuilder strA = new StringBuilder();
        StringBuilder strB = new StringBuilder();
        Elements table = doc.select(".noborder");
        for (int i = 0; i < 11; i++) {
            strA.append(table.text().substring(0,5)).append(" ").append(table.text().substring(13,17)).append(" \n\n");
            double a = Double.parseDouble((table.text().substring(13,17).replaceAll("," , ".")));
            table = table.prev();
        }
        return strA.toString();
    }

    public static String getServer( String url) throws IOException, ParseException {


            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            StringBuilder sc1 = new StringBuilder(response.toString());
            StringBuilder sc2 = new StringBuilder();
            sc2.append(sc1.substring(0, sc1.indexOf("12"))
                    .replaceAll("[{\"]" , "")
                    .replaceAll("\"" , "")
                    .replaceAll("},id:" , "\n\n")
                    .replaceAll(",", " "));



        return sc2.toString();
    }
}
