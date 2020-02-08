public class Tire{

	private double _airPressure; //air pressure
	private final double _airPressureRecommended; // air pressure recomended
	private boolean _flat; // flat 
	public static int _numTire; // Number of Tires
	public static Tire[] _tires; // Array with Tires

	public Tire( double airPressure , double airPressureRecommended){ // Tire constructor
		_airPressureRecommended = airPressureRecommended;
		_airPressure = airPressure;
		_flat = false;
	}

	public double getAirPressure(){ // Get Air Pressure
		return _airPressure;
	}

	public double getAirPressureRecommended(){ // Get Air Pressure Recommended
		return _airPressureRecommended;
	}

	public void setAirPressure(double airPressure){ // Set Air Pressure
		_airPressure = airPressure;

		if (_airPressure > 1.5* _airPressureRecommended){ _flat = true;} 

		/** If ait pressure is bigger than 1.5 
		of air! pressure recommended 
		then is flat */
	}

	public boolean isFlat(){ // Is Flat
		return _flat;
	}

	public boolean isEmpty(){ // Is Empty
		return (_airPressure < 0.8 * _airPressureRecommended);
	}

	public static boolean mountTire(Tire[] _tires){
		if (tires.length != _numTire)
			return false;

		double _airPressureRecommended = _tires[0].airPressureRecommended;

		for (Tire _tires : tires){
			if (tires.airPressureRecommended != _airPressureRecommended)
				return false;
		}
		return true;
	}
}