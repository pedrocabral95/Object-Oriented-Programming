public class NumMemo extends Numb
{

	private int _numMemory;


	public NumMemo(int numMemory)
	{
		Numero(numMemory);
		_numMemory = getNum();
	}

	public void undo()
	{
		int aux = _num;
		_num = _numMemory;
		_numMemory = aux;
	}

	public int getNumMemory()
	{
		return _numMemory;
	}

	@Override
	public void setNum(int num)
	{
		_numMemory = _num;
		_num = num;
	}






}