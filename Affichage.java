package Affichage;
import API.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import API.MetaWeather;
import API.Prev;
import API.Yahoo;


public class Affichage {

	private Arguments argms;
	
	
	public Affichage(Arguments argms){
		this.argms=argms;
	}
		
	public void get_info()
	{

		for (int i =0; i < 3; i++){
			//Affiche <T a>
		}
		meta.description("| MetaWeather |", this.argms.humidity, this.argms.wind, this.argms.fahrenheit);


		prev.description("|   P-Meteo   |", this.argms.humidity, this.argms.wind, this.argms.fahrenheit);


		yaho.description("|   Yahoo     |", this.argms.humidity, this.argms.wind, this.argms.fahrenheit);
	}
	
	public void description(String title,boolean enable_humidity,boolean enable_wind,boolean fahrenheit){
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
				if(fahrenheit==true) {
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
		if(enable_humidity==true){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
		}
		// Printing wind speed
		if(enable_wind==true){
			System.out.println("");
			System.out.print("|             |");
			for (int i =0;i<this.nb_days;i++){
				if (this.h[i] == -1)
				{
					System.out.print("  "+"--"+"    |");
				}
				else {
				System.out.print(" "+String.format("%02d", (int)this.w[i])+"km/h |");
				} woeid = parse_object(location_search,"woeid");
				Object location = parse_url("https://www.metaweather.com/api/location/"+String.valueOf(woeid));
				Object consolidated_weather = parse_object(location,"consolidated_weather");
				for(int i=0;i<super.nb_days;i++)
					{
						Object infos = parse_array(consolidated_weather,i);
						super.t[i]=(int)(double)(parse_object(infos,"the_temp"));
						super.h[i]=(int)(long)(parse_object(infos,"humidity"));
						super.w[i]=(int)(double)(parse_object(infos,"wind_speed"));
					}
			}
		}
		System.out.println("");
	}
	
	public void cmdread() throws FileNotFoundException
	{
		printout();
		if (this.argms.o_file == true)
		{	
			PrintStream out = new PrintStream(new FileOutputStream(this.argms.File));
			System.setOut(out);
			printout();

		}

		if (this.argms.a_file == true)
		{
			PrintStream out = new PrintStream(new FileOutputStream(this.argms.File,true));
			System.setOut(out);
			printout();
		}

	}
	public void first_line(){
		System.out.print("+-------------+");
		for (int i =0;i<this.argms.nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
		System.out.print("|             |");
		for (int i =0;i<this.argms.nb_days;i++){
			System.out.print("  J+"+i+"   |");
		}
		System.out.println("");
	}
	public void last_line(){
		System.out.print("+-------------+");
		for (int i =0;i<this.argms.nb_days;i++){
			System.out.print("--------+");
		}
		System.out.println("");
	}
	public void printout(){
		first_line();
		get_info();
		last_line();
	}
}
