import java.lang.Exception;

public class TempException extends Exception {

    private int _temp;

    public TempException(int temp, String message){ 
        super(message);
        setTemp(temp); //_temp = temp;
    }

    public int getTemp() {
        return _temp;
    }

    public void setTemp(int temp) {
        _temp = temp;
    }
}