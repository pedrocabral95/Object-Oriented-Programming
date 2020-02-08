package po.virtual;

import po.drink.CoffeeCup;
import po.drink.EmptyCupException;
import po.virtual.CoffeeCup;

public class Person{

    private String _name;
    private int _tempInf;
    private int _tempSup;

    public Person (String name, int tempInf, int tempSup){
        setName(name);
        setTempInf(tempInf);
        setTempSup(tempSup);
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public int getTempInf() {
        return _tempInf;
    }

    public void setTempInf(int tempInf) {
        _tempInf = tempInf;
    }

    public int getTempSup() {
        return _temSup;
    }

    public void setTempSup(int tempSup) {
        _tempSup = tempSup;
    }

    public void drinkCoffeeCup(CoffeeCup coffee) throws TempException, EmptyCupException{
        String message;
        if (coffee.getTemperature() < _tempInf || coffee.getTemperature() > _tempSup ) {
            if (coffee.getTemperature() < _tempInf {
                message = "Temperatura do café demasiado fria: " + coffee.getTemperature();
            } if (coffee.getTemperature() > _tempSup) {
                message = "Temperatura do café demasiado quente: " + coffee.getTemperature();
            }
            throw new TempException(coffee.getTemperature(), message);
        }
        coffee.drink();
    }

    public static void main(String[] args) {
        Person person = new Person("Rui", 20, 35);
        CoffeeCup coffeeCup = new CoffeeCup(34, 5);
        try {
          person.drinkCoffeeCup(coffeeCup);
        }
        catch (TempException e) {
            System.out.println(e.getMessage());
        }
        catch (EmptyCupException e) {
            System.out.println("Chávena vazia!");
        }
    }
}