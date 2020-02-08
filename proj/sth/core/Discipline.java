package sth.core;


import sth.core.Parser;
import sth.core.School;
import sth.core.Teacher;
import sth.core.Course;
import sth.core.Person;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchProjectIdException;
import java.util.Comparator;
import java.io.IOException;
import java.io.Serializable;

public class Discipline implements Serializable {
	
	// Name of discipline
	private String _name;

	// Max capacity of representatives
	private final int _capacidade = 6;

	// People associated with the discipline
	private HashMap<Integer,Person> _persons;

	// Students enroled in the discipline
	private HashMap<Integer,Student> _students;

	// Projects associated with the discipline
	private HashMap<String,Project> _projects;

	// Course associated with discipline
	private Course _course;

	 /** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;

	/**
	* @param name
	* @param course
	* Class Discipline Construtor
	*/

	public Discipline(String name, Course course) {
		_name = name;
		_course = course;
		_persons = new HashMap<Integer,Person>();
		_students = new HashMap<Integer,Student>(6);
		_projects = new HashMap<String,Project>();
	}

	/**
	* @return discipline name
	*/	

	public String getName() {
		return _name;
	}

	/**
	* @return associated course
	*/

	Course getCourse() {
		return _course;
	}

  void removeStudent(int id) throws NoSuchPersonIdException {
    if (_students.containsKey(id)) {
      _students.remove(id);
    } else throw new NoSuchPersonIdException(id);
  }
	/**
	* adds a teacher to the associated people
	*/

	void addTeacher(Teacher teacher) {
		_persons.put(teacher.getId(),teacher);
	}

	/**
	* @param s
	* @return true if student enrolled in discipline. Otherwise, return false
	*/

	boolean areYouEnrolled(Student s) {
		if (_students.containsValue(s))
				return true;
		return false;
	}

		/**
	* @param student
	* Enrolls a student in the discipline
	*/
		
	void enrollStudent(Student student) {
		_students.put(student.getId(),student);
		_persons.put(student.getId(),student);
	}

		/**
	* @return List<String> with all the students enrolled in class
	*/

	List<Student> getAllStudents() {
		List<Student> students = new ArrayList<Student>(_students.values());
		return students;
	}

	/**
	* @param project
	* @throws DuplicateProjectIdException
	* Creates a project associated with the discipline
	*/

	void createProject(String project) throws DuplicateProjectIdException {
		if (_projects.containsKey(project))
			throw new DuplicateProjectIdException(getName(),project);
		//System.out.println(getName());
		Project p = new Project(this,project);
		_projects.put(project, p);
		//System.out.println(p);
	}

	boolean hasProject(String project) {
		return _projects.containsKey(project);
	}

	/**
	* @param project
	* @throws NoSuchProjectIdException
	* Closes a project associated with the discipline
	*/

  boolean canCreate() throws NoSuchDisciplineIdException {
    if (_students.size() >= 5)
      return true;
    return false;
  }
	void closeProject(String project) throws NoSuchProjectIdException {
		if (_projects.containsKey(project)) {
				Project p = _projects.get(project);
				if (p.isClosed())
					return;
				p.close();
		}
		throw new NoSuchProjectIdException(project);
	} 

		/**
	* @param project
	* @throws NoSuchProjectIdException
	* @return a project associated with the discipline
	*/

	Project getProject(String project) throws NoSuchProjectIdException {
		if (_projects.containsKey(project))
			return _projects.get(project);
		throw new NoSuchProjectIdException(project);
	}

	/**
	* @return projects associated with the discipline
	*/
  int getNumberProjects() {
    return _projects.size();
  }
  int getNumberSub() {
    List<Project> pro = (List<Project>) _projects.values();
    int k = 0;
    for (Project p : pro) {
      k += p.getSubmissions();
    }
    return k;
  }
	List<Project> getProjects() {
		List<Project> output = new ArrayList<Project>(_projects.values());
		return output;
	}

		/**
		* @return String with the description of the discipline
		*/

	public String toString() {
		return _course.toString() + " - " + _name;
	}
}