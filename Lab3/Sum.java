import java.lang.Integer;

public class Sum
{
  public static Sum _s = null;
  
  private int _total;

  public Sum(int t) 
  {
    _total = t; // Fixed
  }

 public void computeVectorSum(int[] v)
 {
    int iterator = 0;
    _total = 0;
    int vLen = v.length; // Fixed

    while (iterator < vLen)
    {
        _total += v[iterator++];
    }
 }

  public int getTotal() //Fixed
  { 
    return _total;
  }
}