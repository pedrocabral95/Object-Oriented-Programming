package java.po.virtual
import java.po.drink.CoffeeCup

public class Person
{
	private String _nome;
	private int _frio;
	private int _quente;

	public Person(String nome, int quente, int frio)
	{
		_nome = nome;
		_frio = frio;
		_quente = quente;
	}

	public String getName()

	public void drink(CoffeeCup coffee) throws HighTemperatureException, LowTemperatureException 
    {
    	String message
    	if (coffee.getTemperature() > _quente)
    	{
    		throw new HighTemperatureException("Quente" + coffee.getTemperature());
    		
    		
    	}

    	if ( 0 < coffee.getTemperature() <= _frio)
    	{
    		throw new LowTemperatureException("Frio" + coffee.getTemperature());
    		
    		
    	}
    }
    public static void main (String[] args)
    {
    	Person p1 = new Person()


    	
    }

}