public class MainCar{

	public static void main(String[] args){

		Scanner input = new Scanner(System.in);
		System.out.println("Introduce the brand\t");
		String brand = input.next();

		Scanner input1 = new Scanner(System.in);
		System.out.println("Introduce the SpeedMax\t");
		String speedMax = input1.nextDouble();

		Scanner input2 = new Scanner(System.in);
		System.out.println("Introduce the Number of Tires\t");
		String numTires = input2.nextInt();


		Car newCar = new Car(brand, speedMax , numTires);

		newCar.getKm();

		newCar.getBrand();

		

	}
}