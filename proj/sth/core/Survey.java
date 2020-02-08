package sth.core;


import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Student;
import sth.core.Teacher;
import sth.core.Discipline;
import sth.core.Submission;
import sth.core.DisplayNote;
import sth.core.Observer;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import java.io.Serializable;


public class Survey implements Serializable, Subject {

	// Name of the survey
	private String _name;

	// Discipline associated with the discipline
	private Discipline _discipline;

  private List<Student> _observers;
	// Current condition of survey
	private Condition _condition;

	// Project associated with survey
	private Project _project;

	// Number of answers
	private int _answers = 0;

	// Minimum hours
	private int _min = 0;

	// Average hours
	private int _med = 0;

	// Total of hours spent
	private int _totalHours = 0;

	// Maximum hours
	private int _max = 0;

	/*
	* Abstract condition class.
	*
	* This class is internal so that it has access to the surveys's
	* internal state. Actual states are subclasses which must use this class'
	* protected interface.
	*/

	public abstract class Condition implements java.io.Serializable {

		public abstract String toString();

		public abstract void open();

		public abstract void close();

		public abstract void finalize();

		public abstract boolean isCreated();

		public abstract boolean isOpened();

		public abstract boolean isClosed();

		public abstract boolean isFinalized();

		/**
		* Define the survey's new state.
		*
		*@param newCondition
		*         the new condition 
		*/

		protected void setCondition(Condition newCondition) {
			_condition = newCondition;
		}

		/**
		* This method is needed so that new conditions can be created.
		*
		*@return the survey.
		*/

		protected Survey getSurvey() {
			return Survey.this;
		}
	}

	/**
	* @param name
	* @param discipline
	* @param project
	* Class Survey Constructor
	*/

	public Survey(String name, Discipline discipline, Project project) {
		_name = name;
		_discipline = discipline;
		_condition = new Created(this);
		_project = project;
    _observers = _discipline.getAllStudents();

	}

	/**
	* @return current state
	*/

	public Condition getState() {
		return _condition;
	}

	/**
	* @return associated project
	*/

	public Project getPoject() {
		return _project;
	}

	/**
	* @return associated discipline
	*/

	public Discipline getDiscipline() {
		return _discipline;
	}

	/**
	* Changes state to opened
	*/

	public void open() {
		_condition.open();
		notifyPersons();
	}

	/**
	* Changes state to closed
	*/

	public void close() {
		_condition.close();
		notifyPersons();
	}

	/**
	* Changes state to finalized
	*/

	public void finalize() {
		_condition.finalize();
	}

  /**
  * @param note
  * Notifies with note all of the associated people
  */
  public void notifyPersons() {
    for (Student s: _observers) {
      if (this.isClosed()) {
        s.update("Resuldados do inquérito do projeto " + _project.getName() + " da disciplina " + _discipline.getName());
      }
      if (this.isOpened()) {
        s.update("Pode preencher inquérito do projeto " + _project.getName() + " da disciplina " + _discipline.getName());
      
      }
    }
  }
	/**
	* @param hours
	* @param message
	* Adds a answer to survey
	*/

	public void addAnswer(int hours, String message) {
		_answers++;
		_totalHours += hours; 
		if (hours < _min) {
			_min = hours;
			return;
		}
		if (hours > _max) {
			_max = hours;
			return;
		}
		return;
	}

	/**
	* @return boolean 
	*         true if the condition is created otherwise its false
	*/

	public boolean isCreated(){
		return _condition.isCreated();
	}

	/**
	* @return boolean 
	*         true if the condition is opened otherwise its false
	*/

	public boolean isOpened(){
		return _condition.isOpened();
	}

	/**
	* @return boolean 
	*         true if the condition is closed otherwise its false
	*/

	public boolean isClosed(){
		return _condition.isClosed();
	}

	/**
	* @return boolean 
	*         true if the condition is finalized otherwise its false
	*/

	public boolean isFinalized(){
		return _condition.isFinalized();
	}

	/**
	* @return average hours
	*/

	public int getMedia() {
    if (hasAnswers())
		  return _totalHours / _answers;
    return _answers;
	}

	/**
	* @return minimum hours
	*/

	public int getMin() {
		return _min;
	}

	/**
	* @return max hours
	*/

	public int getMax(){
		return _max;
	}

	/**
	* @return survey answers
	*/

	public int getAnswers() {
		return _answers;
	}

  public boolean hasAnswers(){
    if (_answers == 0)
      return false;
    return true;
  }

	/**
 	* @return String survey type
  	*         Survey String is returned
  	**/
	public String toString() {
		return _discipline.getName() + " - " + _project.getName() + this.getState().toString();
	}
}