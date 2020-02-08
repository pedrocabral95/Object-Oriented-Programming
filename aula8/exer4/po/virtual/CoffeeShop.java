import po.drink.CoffeeCup;
import po.virtual.Person;

public class CoffeeShop{

    private String _name;

    public CoffeeShop(String name){
        _name = name;
    }

    public boolean serveCustomer(Person person, CoffeeCup coffee) {
        for (int i = 0; i < 2; i++) {
            try {
                person.drinkCoffeeCup(coffee);
                return true;
            } catch (TempException e) {
                System.out.println(e.getMessage());
                if (coffee.getTemperature() < person.getTempInf()) {
                    coffee.heat();
                } if (coffee.getTemperature() < person.getTempSup()) {
                    coffee.coll();
                }
            } catch (EmptyCupException e) {
                coffee.fill();
            } catch (AlreadyFullException e) {
                //something
            } finally {
                System.out.println("Café servido!");
            }
        }
        return false;
    }
}