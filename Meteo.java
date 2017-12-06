

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
}