

public class Car
{
	private String _marca;

	public Car(String marca)
	{
		_marca = marca;
	}

	public void acelerar()
	{
		System.out.println("VRUM VRUM");
	}

	public void travar()
	{
		System.out.println("GRRR GRRR");
	}

	public void buzinar()
	{
		System.out.println("PII PII");
	}

	public void abastecer(int quantidade, int id)
	{
		
	}

	public String getMarca()
	{
		return _marca;
	}
}

