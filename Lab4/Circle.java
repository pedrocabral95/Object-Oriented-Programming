public class Circle extends Forms
{

	public Circle(int origin, int radius)
	{
		_origin = origin;
		_extreme = radius;
	}

	public void move(int origin , int radius)
	{
		_origin = origin;
		_extreme = radius;
	}

	public void draw()
	{
		System.out.println(_origin);
		System.out.println(_extreme);

	}
}