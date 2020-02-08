package sth.core;

import sth.core.Survey.Condition;
import sth.core.Finalized;
import sth.core.Discipline;
import sth.core.Course;
import sth.core.Person;
import sth.core.Survey;

public class Opened extends Survey.Condition implements java.io.Serializable{
	
	/**
	* @param survey
	* Class Constructor
	*/

	public Opened(Survey survey){
		survey.super();
	}

	/**
	* Raises Exception
	*/

	@Override
	public void open(){
		//raise exception
	}

	@Override
	public void close(){
		setCondition(new Closed(getSurvey()));
	}

	/**
	* Raises Exception
	*/

	@Override
	public void finalize(){
		//raise exception
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
	* @return true
	*/

	@Override
	public boolean isOpened(){
		return true;
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
		return " (aberto)";
	}

}