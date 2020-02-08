
public class EstacaoServico
{

	private int _capacidade;
	private int[] _bombas;

	public EstacaoServico(int capacidade)
	{
		_capacidade = capacidade;
		int[] _bombas = new int[10];
	}

	public int abastecer(int quantidade, int id)
	{
		int antes = _bombas[id];
		_bombas[id] -= quantidade;
		int depois = _bombas[id];
		return antes-depois;
	}

	public int getQuantidade()
	{
		return _capacidade;
	}

	public int getQuantidadeDeBomba(int id)
	{
		return _bombas[id];
	}

	public void abastecerBomba(int quantidade)
	{
		_capacidade += quantidade;
	}

	public static void main(String[] args)
	{
		EstacaoServico leiria = new EstacaoServico(200);
		Car car0 = new Car("XPTO0");
		Car car1 = new Car("XPTO1");
		Car car2 = new Car("XPTO2");
		Car car3 = new Car("XPTO3");
		Car car4 = new Car("XPTO4");
		HCar hcar0 = new HCar("XPTO0");
		HCar hcar1 = new HCar("XPTO1");
		HCar hcar2 = new HCar("XPTO2");
		HCar hcar3 = new HCar("XPTO3");
		HCar hcar4 = new HCar("XPTO4");

		//System.out.println(hcar0.abastecer(150, 2));
		System.out.println(hcar0.getMarca());
		hcar0.buzinar();
		hcar0.travar();
		hcar0.acelerar();
		System.out.println(hcar0.getAcelera());
		System.out.println(hcar0.getTrava());



	}
}