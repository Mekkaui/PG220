

public abstract class Meteo {
	protected double t[];

	protected long h[];

	protected int nb_days;

	protected double w[];

	public Meteo(int nb_days){
		this.nb_days = nb_days;
		this.t = new double[nb_days];
		this.h = new long[nb_days];
		this.w = new double[nb_days];
	}
	public double get_t(int day){
		return this.t[day];
	}
	public long get_h(int day){
		return this.h[day];
	}
	public double get_w(int day){
		return this.w[day];
	}











	/*protected int t[];

		  protected int h[];

		  protected int nb_days;

		  protected String city;

		  protected int v[];

		  public Meteo(String city, int nb_days){
			  this.nb_days = nb_days;
			  this.city = city;
			  this.t = new int[nb_days];
			  this.h = new int[nb_days];
			  this.v = new int[nb_days];
		  }
		  public int get_temp(int day){
			  return this.t[day];
		  }

		  public int get_h(int day){
			  return this.h[day];
		  }
		  public int get_v(int day){
			  return this.v[day];
		  }

		  public String getCity() {
			return this.city;
		}

		  public void setTemp(int day,int temp){
			  this.t[day]= temp;
		  }

		  public void setH(int day,int h){
			  this.h[day]= h;
		  }

		  public void setV(int day,int v){
			  this.v[day]= v;
		  }

		  public void setCity(String city) {
			this.city = city;
		}*/


}