package java.po.drink

public class CoffeeCup
{
    private int _temperature;
    private int _quantityOfCoffee;

    public CoffeeCup(int val, int quantity) {
        _temperature = val;
        _quantityOfCoffee = quantity;
    }

    public int getTemperature() {
        return _temperature;
    }

    public int drink() throws EmptyCupException
    {

    	if (_quantityOfCoffee == 0)
        
        	throw new EmptyCupException();
      	_quantityOfCoffee = 0;
     	
    }

    
    //...

} 