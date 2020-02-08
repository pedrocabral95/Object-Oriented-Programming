package sth.core;


import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Student;
import sth.core.Teacher;
import sth.core.Discipline;
import sth.core.Survey;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.SubmissionComparator;

import java.io.IOException;

public class Project implements java.io.Serializable{

	// Serial number for serialization
	private static final long serialVersionUID = 201810051538L;

	// Name of the project
	private String _name;

	// Description of the project
	private String _description;

	// Submissions of the project
	private HashMap<Integer,Submission> _submissions;

	// Number of submissions
	private int _nSubmissions;

	// Discipline associated with the project
	private Discipline _discipline;

	// Students enroled in discipline that can submit projects
	private HashMap<Integer,Student> _students;

	// Survey of the project
	private Survey _survey;

	// State of the project
	// Starts opened
	private boolean _closed = false;

	/**
	* @param discipline
	* @param name
	* Class Project Constructor
	*/

	public Project(Discipline discipline, String name) {
		_name = name;
		_discipline = discipline;
		_submissions = new HashMap<Integer,Submission>();
		_survey = null;//new Survey(name,discipline,this);
	}

	/**
	* @return project name 
	*/

	public String getName() {
		return _name;
	}

	/**
	* @return project description 
	*/

	String getDescription() {
		return _description;
	}

	/**
	* @param s
  * @param d
  * @param p
	* Sets a new survey as the project's associated survey 
	*/

	void setSurvey(String s,  Discipline d, Project p) {
		_survey = new Survey(s,d,p);
	}

		/**
	* Change project to closed state 
	*/

	void close()	{
		_closed = true;
			_survey = new Survey(_name,_discipline,this);
		_survey.open();
	}

	/**
	* @return boolean
	*         true if its closed otherwise its false 
	*/

	boolean isClosed() {
		return _closed;
	}

	/**
	* @return associated survey 
	*/

	Survey getSurvey() {
		return _survey;
	}

	/**
	* @param student
	* @param message
	* Adds a new project submission 
	*/

	void addSubmission(Student student, String message) {
			_submissions.put(student.getId(),new Submission(message,this,student));
			_nSubmissions++;
	}

	/**
	* Changes current description to a new one
	*/

	void setDescription(String description) {
		_description += description;
	}

	/**
	* @param s
	* @return boolean
	*         true if student already submitted otherwise its false 
	*/
	boolean hadSubmited(Student s) {
		if (_submissions.containsKey(s.getId()))
				return true;
		return false;
	}

		/**
	* @return number of submissions 
	*/

	int getSubmissions() {
		return _nSubmissions;
	}

	/**
	* @return List<Submission> with all project submission sorted by student id 
	*/

	List<Submission> getProjectSubmissions(){
		List<Submission> output = new ArrayList<Submission>(_submissions.values());
		Collections.sort(output, new SubmissionComparator());
		return output;
	}

  void cancelSurvey(){
    _survey = null;
  }

		/**
		* @return String project type
		*         Project String is returned
		**/
		
	public String toString(){ 
		return "* " + this.getName();
	}
}