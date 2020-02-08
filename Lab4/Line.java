public class Line extends Forms
{

	public Line(int origin, int extreme)
	{
		_origin = origin;
		_extreme = extreme;
	}

	public void move(int origin , int extreme)
	{
		_origin = origin;
		_extreme = extreme;
	}

	public void draw()
	{
		System.out.println(_origin);
		System.out.println(_extreme);

	}
}