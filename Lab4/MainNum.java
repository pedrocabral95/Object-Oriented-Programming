import java.util.Scanner;

public class MainNum
{

	public static void main(String[] args)
	{
		Scanner input4 = new Scanner(System.in);
		System.out.println("Insert number 1");
		int num11 = input4.nextInt();

		Scanner input2 = new Scanner(System.in);
		System.out.println("Insert number 2");
		int num22 = input2.nextInt();
		else
		{
			Num num111 = new Num(num11);

			Num num222 = new Num(num22);
		
			System.out.println('\n');

			System.out.println(num111.getNum());

			System.out.println(num222.getNum());
		}
	}
}