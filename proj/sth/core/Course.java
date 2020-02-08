package sth.core;


import sth.core.Parser;
import sth.core.School;
import sth.core.Student;
import sth.core.Discipline;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import java.io.Serializable;
import java.io.IOException;

/**
* Class for Courses
*/
public class Course implements Serializable {

	// Name of the course
	private String _name;

	// Disciplines associated with course
	private HashMap<String,Discipline> _disciplines;

	// Students associated with course
	private HashMap<Integer,Student> _students;

	// Representatives associated with course
	private HashMap<Integer,Student> _representatives;

	// Max number of representatives that can be associated
	private int _nMaxRepresentative;

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	/**
	* Class Constructor
	*/

	public Course(String name) {
		_name = name;
		_disciplines = new HashMap<String,Discipline>();
		_students = new HashMap<Integer,Student>();
		_representatives = new HashMap<Integer,Student>();
	}

	/**
	*  @return String which contains course name
	*/

	public String getName() {
		return _name;
	}

	/**
	*  @return List<Discipline> which contains all course's disciplines
	*/

	List<Discipline> getDisciplines() { 
		List<Discipline> _dis = new ArrayList<Discipline>(_disciplines.values());
		return _dis;
	}

	/**
	*  @param discipline
	*  This method add's a discipline to a course
	*/

	void addDiscipline(Discipline discipline) {
		_disciplines.put(discipline.getName(),discipline);
	}

	/**
	*  @param discipline
	*  @return boolean if course contais a discipline
	*  This method verify is course contains a discipline
	*/

	boolean containDiscipline(String discipline) {
		if (_disciplines.containsKey(discipline))
			return true;
		return false;
	}

	/**
	*  @param discipline
	*  @return Discipline ask by a client
	*  This method return a course discipline
	*/

	Discipline getDiscipline(String discipline) throws NoSuchDisciplineIdException {
		if (containDiscipline(discipline))
			return _disciplines.get(discipline);
		else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	*  @param discipline
	*  This method add a student to course
	*/

	void addStudent(Student student) {	
		_students.put(student.getId(),student);
	}

	/**
	*  @param student
	*  This method add a representative to a course if it's possible
	*/

	void addRepresentative(Student student) {
		boolean t = true;
		if (_nMaxRepresentative < 7){
			int id = student.getId();
			Student p = _students.get(id);
			p.setRepresentative(t);
			_representatives.put(id,p);
			_nMaxRepresentative++;
		}
	}

  void makeRepresentative(int id) throws NoSuchPersonIdException {
    if (_students.containsKey(id)) {
      if (!_students.get(id).isRepresentative()) {
        addRepresentative(_students.get(id));
        return;
      } 
      return;
    } else throw new NoSuchPersonIdException(id);
  }

	/**
	*  @param student
	*  This method remove a representative to a course
	*/

	void removeRepresentative(Student student) {
		boolean f = false;
		if (student.isRepresentative()) {
			int id = student.getId();
			Student p = _students.get(id);
			p.setRepresentative(f);
			_representatives.remove(id,p);
			_nMaxRepresentative--;
		}
	}

	/**
	* @param discipline
	* @return Discipline
	* This method parse a discipline, return discipline if course contains that discipline
	*   else create a new discipline, add to a course disciplines and return that
	*/

	public Discipline parseDiscipline(String discipline) {	
		if (_disciplines.containsKey(discipline))
			return _disciplines.get(discipline); 
		else {
			Discipline dis = new Discipline(discipline,this);
			this.addDiscipline(dis);
			return dis;
		}
	}

	/**
	*  @return String 
	*  This method return discipline to string
	*/
	
	public String toString(){
		return "* " + _name;
	}
}