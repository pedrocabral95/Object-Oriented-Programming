public class Square extends Forms
{

	public Square(int origin, int sidePoint)
	{
		_origin = origin;
		_extreme = sidePoint;
	}

	public void move(int origin , int sidePoint)
	{
		_origin = origin;
		_extreme = sidePoint;
	}

	public void draw()
	{
		System.out.println(_origin);
		System.out.println(_extreme);

	}
}