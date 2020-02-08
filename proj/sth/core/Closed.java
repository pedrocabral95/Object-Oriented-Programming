package sth.core;
import sth.core.Survey.Condition;
import sth.core.Finalized;
import sth.core.Discipline;
import sth.core.Course;
import sth.core.Person;
import sth.core.Survey;


public class Closed extends Survey.Condition implements java.io.Serializable {
  	
	/**
	* @param survey
	* Class Constructor
	*/

  	public Closed (Survey survey){
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
	* Does nothing
	*/

	@Override
	public void close(){
		//do nothing
	}

	/**
	* Changes condition to Finalized
	*/

	@Override
	public void finalize(){
		setCondition(new Finalized(getSurvey()));
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
	* @return true
	*/

	@Override
	public boolean isClosed(){
		return true;
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
		return " (fechado)";
	}
}