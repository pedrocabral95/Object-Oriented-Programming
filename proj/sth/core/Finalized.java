package sth.core;
import sth.core.Survey.Condition;
import sth.core.Finalized;
import sth.core.Discipline;
import sth.core.Course;
import sth.core.Person;
import sth.core.Survey;
public class Finalized extends Survey.Condition implements java.io.Serializable {
	
	/**
	* @param survey
	* Class Constructor
	*/

	public Finalized (Survey survey) {
		survey.super();
	}

	/**
	* Raises Exception
	*/

	@Override
	public void open(){
		//raise exception
	}

	/**
	* Raises Exception
	*/

	@Override
	public void close(){
		//raise exception
	}

	/**
	* Does nothing
	*/

	@Override
	public void finalize(){
		//do nothing
	}

	/**
	* Checks if current condition is Created
	* @return false
	*/

	@Override
	public boolean isCreated(){
		return false;
	}

	/**
	* Checks if current condition is Opened
	* @return false
	*/

	@Override
	public boolean isOpened(){
		return false;
	}

	/**
	* Checks if current condition is Closed
	* @return false
	*/

	@Override
	public boolean isClosed(){
		return false;
	}

	/**
	* Checks if current condition is Finalized
	* @return true
	*/

	@Override
	public boolean isFinalized(){
		return true;
	}

	/**
	* @return String corresponding to the current state
	*/

	@Override
	public String toString(){
		return "";
	}
}