
public class HCar extends Car
{
	private int _numeroAceleracoes;
	private int _numeroTravagens;

	public HCar(String marca)
	{	
		super(marca);
		_numeroTravagens = 0;
		_numeroAceleracoes = 0;
	}

	public void buzinar()
	{
		System.out.println("POO POO");
	}

	public int getAcelera()
	{
		return _numeroAceleracoes;
	}

	public int getTrava()
	{
		return _numeroTravagens;
	}

	public void acelerar()
	{
		_numeroAceleracoes++;
	}

	public void travar()
	{
		_numeroTravagens++;
	}
}