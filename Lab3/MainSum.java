public class MainSum
{

  public static void main(String[] args)
  {
    int i;
    int argLen = args.length; //Fixed
    int[] v = new int[argLen];
 
    Sum sum = new Sum(argLen); //Fixed
    
    for (i = 0; i < argLen; i++) // Fixed
    { 
      v[i] = Integer.parseInt(args[i]);
    }

    Sum._s = sum;

    sum.computeVectorSum(v);

    System.out.println(sum.getTotal());
  }
}