
abstract class Meteo {
		  protected int t[];

		  protected int h[];
		  
		  protected int nb_days;
		  
		  protected int v[];
		  
		  public Meteo(int nb_days){
			  this.nb_days = nb_days;
			  this.t = new int[nb_days];
			  this.h = new int[nb_days];
			  this.v = new int[nb_days];
		  }
		  int get_temp(int day){
			  return this.t[day];
		  }
		  int get_humdity(int day){
			  return this.h[day];
		  }
		  int get_vitesse(int day){
			  return this.v[day];
		  }

		  
}
