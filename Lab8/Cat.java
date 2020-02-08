import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.*;
public class Cat
{
	public static void cat (File file) throws FileNotFoundException, IOException
	{
		RandomAccessFile input = null;
		String line = null;
		//File file = new File("abcd.txt");
		try
		{ 
			input = new RandomAccessFile(file,"r");
			while ((line = input.readLine()) != null)
			{
				System.out.println(line);
			}

		}
		catch (FileNotFoundException fnf)
		{
			System.out.println("FIcheiro Invalido ou incapaz de abrir");
		}
		catch (IOException ioe)
		{
			System.out.println("Fim de linha ou nao ha texto");
		}

		finally
		{
			try
			{
				if (input != null)
				{
					input.close();
				}
			}
			catch (IOException ioeclose)
			{
				System.out.println("Incapaz de fechar ficheiro");
			}
		}
	}		
}