import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class Meteo {
	protected int t[];

	protected int h[];

	protected int nb_days;

	protected int w[];

	public Meteo(int nb_days){
		this.nb_days = nb_days;
		this.t = new int[nb_days];
		this.h = new int[nb_days];
		this.w = new int[nb_days];
		Arrays.fill(this.t , -9999);
		Arrays.fill(this.h , -1);
		Arrays.fill(this.w , -1);
	}
	public int get_t(int day){
		return this.t[day];
	}
	public int get_h(int day){
		return this.h[day];
	}
	public int get_w(int day){
		return this.w[day];
	}
	public void description(String title,int enable_humidity,int enable_wind,int fahrenheit){
		System.out.print("+-------------+");
		for (int i =0;i<this.nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
		System.out.print(title);
		
		// Printing Degree 
		for (int i =0;i<this.nb_days;i++){
			if (this.t[i] != -9999 )
			{
				if(fahrenheit!=0) {
					int far = (int)( this.t[i] * 1.8 ) + 32;
					System.out.print("  "+String.format("%02d", (int)far)+"°F  |");
				}
				else {
					System.out.print("  "+String.format("%02d", (int)this.t[i])+"°C  |");
				}
			}
			else {
				System.out.print("  "+"--"+"    |");
			}
			
		}
		
		// Printing humidity
		if(enable_humidity!=0){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
				if (this.h[i] == -1)
				{
					System.out.print("  "+"--"+"    |");
				}
				else {
				System.out.print("  "+  String.format("%02d", (int)this.h[i]) +"%   |");
				}
			}
		}
		// Printing wind speed
		if(enable_wind!=0){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
				if (this.h[i] == -1)
				{
					System.out.print("  "+"--"+"    |");
				}
				else {
				System.out.print(" "+String.format("%02d", (int)this.w[i])+"km/h |");
				}
			}
		}
		System.out.println("");
	}
	public String DataQuery(String url) throws Exception{
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
	    
	   
	   
	   FileWriter log = new FileWriter("requete.log",true);
	   Date d = new Date();
	   log.write("["+d.toString()+"] ["+responseCode+"]\n");
	   log.write(url+"\n");
	   log.write("");
	   log.close();
	   
	   return response.toString();
	    
	}
	
	public Object parse_object(Object O,String str) {
		Object ret = ((JSONObject)O).get(str);
		return ret;
	}
	public Object parse_array(Object O,int i) {
		Object ret = ((JSONArray)O).get(i);
		return ret;
	}
	public Object parse_url(String url) throws Exception {
		Object line= null;
		String x = DataQuery(url); // Exception
		JSONParser parser = new JSONParser();
		line = parser.parse(x);
		return line;
	}
	
}
