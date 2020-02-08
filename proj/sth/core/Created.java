package sth.core;
import sth.core.Survey.Condition;
import sth.core.Finalized;
import sth.core.Discipline;
import sth.core.Course;
import sth.core.Person;
import sth.core.Survey;

public class Created extends Survey.Condition implements java.io.Serializable{
	
	/**
	* @param survey
	* Class Constructor
	*/

	public Created(Survey survey){
		survey.super();
	}

	/**
	* Changes condition to Opened
	*/

	@Override
	public void open(){
		setCondition(new Opened(getSurvey()));
	}

	/**
	* Raises Exception
	*/

	@Override
	public void close(){
		//raise exception;
	}

	/**
	* Raises Execption
	*/

	@Override
	public void finalize(){
		//raise exception 
	}

	/**
	* Checks if current condition is Created
	* @return true
	*/

	@Override
	public boolean isCreated(){
		return true;
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
	* @return false
	*/

	@Override
	public boolean isFinalized(){
		return false;
	}

	/**
	* @return String corresponding to the current state
	*/
	
	@Override
	public String toString(){
		return " (por abrir)";
	}

}