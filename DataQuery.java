import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DataQuery {

	String data;
	
	public DataQuery(String url) throws Exception{
		String USER_AGENT = "Mozilla/5.0";
	    
	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	    // optional default is GET
	    con.setRequestMethod("GET");

	    //add request header
	    con.setRequestProperty("User-Agent", USER_AGENT);

	    int responseCode = con.getResponseCode();
	    BufferedReader in = new BufferedReader(
	            new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
	      response.append(inputLine);
	    }
	    in.close();
	    
	   this.data=response.toString();
	   
	   FileWriter log = new FileWriter("requete.log",true);
	   Date d = new Date();
	   log.write("["+d.toString()+"] ["+responseCode+"]\n");
	   log.write(url+"\n");
	   log.write("");
	   log.close();
	    
	}
	public String getData(){
		return this.data;
	}
}
