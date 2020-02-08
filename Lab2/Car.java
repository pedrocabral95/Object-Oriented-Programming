public class Car{

	private String _brand;
	private double _km;
	private double _speedMax;

	public Car(String brand, double speedMax , int numTire){
		_km = 0;
		_speedMax = speedMax;
		_brand = brand;
		Tire._numTire = numTire;

		int creator;

		Tire._tires = new Tire[Tire._numTire];

		for (creator = 0; creator < Tire._numTire; creator++){
			Tire._tires[creator] = new Tire(12,15);

		}
	}

	public double getKm(){
		return _km;
	}

	public String getBrand(){
		return _brand;
	}

	public double getSpeedMax(){
		return _speedMax;
	}

	public void setKm(double km){
		_km = km;
	}

}