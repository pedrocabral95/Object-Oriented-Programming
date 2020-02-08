package sth.core;

import sth.core.Survey;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

/**
*  Class for Answer to a Survey
*/
public class Answer implements java.io.Serializable{

  /** Project message */
	private String _message;

  /** Project hours */
	private int _hours;

  /** Survey atributte */
  private Survey _survey;
  
  /**
  * Class Constructor
  */

	public Answer(int hours, String message) {
		_hours = hours;
		_message = message;
	}

  /**
   * @return String which contains a message
   */

	public String getMessage(){
		return _message;
	}

  /**
   * @return int which contains the project hours to finish the project
   */
  
	public int getHours() {
		return _hours;
	}
}