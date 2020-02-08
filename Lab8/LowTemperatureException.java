package po.drink
import java.lang.Exception;
public class LowTemperatureException extends Exception
{
	private int _temp;
	public LowTemperatureException(int temperature, String message)
	{
		super(message);
		_temp = temperature;
	}

	public int getTemp()
	{
		return _temp;
	}

	public void setTemp(int temperature)
	{
		_temp = temperature;
	}
}