package po.drink;

public class CoffeeCup {
    private int _temperature;
    private int _quantityOfCoffee;

    public CoffeeCup(int val, int quantity) {
        set_temperature(val);
        _quantityOfCoffee = quantity;
    }

    public void setTemperature(int temperature) {
        _temperature = temperature;
    }

    public int getTemperature() {
        return _temperature;
    }

    public void setQuantity(int quant) {
        _quantityOfCoffee= quant;
    }

    public int getQuantity() {
        return _quantityOfCoffee;
    }

    public int drink() throws EmptyCupException {
        if (_quantityOfCoffee == 0)
            throw new EmptyCupException();
        _quantityOfCoffee = 0;
    }

    public void fill() throws AlreadyFullException {
        if (_quantityOfCoffee > 0)
            throw new AlreadyFullException();
        _quantityOfCoffee = 6;
    }

    public void heat(){
        setTemperature(30);
    }

    public void coll(){
        setTemperature(30);
    }
   
} 