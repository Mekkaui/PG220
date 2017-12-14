import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
class Main {

  public static void main(String[] args) throws Exception{

    String USER_AGENT = "Mozilla/5.0";
    String url = "https://www.metaweather.com/api/location/search/?query=bordeaux";

    URL obj = new URL(url);
    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

    // optional default is GET
    con.setRequestMethod("GET");

    //add request header
    con.setRequestProperty("User-Agent", USER_AGENT);

    int responseCode = con.getResponseCode();
    //System.out.println("\nSending 'GET' request to URL : " + url);
    //System.out.println("Response Code : " + responseCode);

    BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
    String inputLine;
    StringBuffer response = new StringBuffer();

    while ((inputLine = in.readLine()) != null) {
      response.append(inputLine);
    }
    in.close();
    
    //System.out.println(response.toString());
    JSONParser parser = new JSONParser();
		
    try{
       Object z = parser.parse(response.toString());
       JSONArray array = (JSONArray)z;
			
       /*System.out.println("The 2nd element of array");
       System.out.println(array.get(0));
       System.out.println();*/

       JSONObject obj2 = (JSONObject)array.get(0);
       int nb_days = 2;
       int h_y=1;
       int w_y=1;
       System.out.print("+-------------+");
       for (int i =0;i<nb_days;i++){
    	   System.out.print("--------+");
       }
       System.out.println("");
       System.out.print("|             |");
       for (int i =0;i<nb_days;i++){
    	   System.out.print("  J+"+i+"   |");
       }
       System.out.println("");
    
           
       MetaWeather meta = new MetaWeather(nb_days);
       meta.update(args[0]);
       meta.description("| MetaWeather |", h_y, w_y);
       /*for (int i =0; i<meta.t.length; i++) {
    	   meta.update(args[0]);
    	   System.out.println("J+0 temp " + (int)meta.get_t(i) + " humidity "+ meta.get_h(i) + " wind " + (int)meta.get_w(i));   
       }*/
       Yahoo yaho = new Yahoo(nb_days);
       yaho.update(args[0]);
       yaho.description("| Yahoo       |", h_y, w_y);


    }catch(ParseException pe){
		
       System.out.println("position: " + pe.getPosition());
       System.out.println(pe);
    }

  }

}