package sth.core;

import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Course;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.ArrayList;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchCourseIdException;
import java.lang.String;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.*;
import java.util.List;
/**
 * School implementation.
 */
public class School implements java.io.Serializable {

	// Serial number for serialization.
	private static final long serialVersionUID = 201810051538L;

	// Name of the school
	private String _name;

	// People associated with the school
	private HashMap<Integer,Person> _people;

	// Courses associated with the scho
	private HashMap<String,Course> _courses;

	//Number of person
	private int _nextPersonId;

	/**
	* @param String nome
	* Class School Constructor
	*/

	public School(String nome) {
		_name = nome;
		_courses = new HashMap<String,Course>();
		_people = new HashMap<Integer,Person>();
	}

		public Person getPerson(int id) throws NoSuchPersonIdException {
			for (Person p : _people.values()) {
				if (p.getId() == id)
				  return p;
			}
			throw new NoSuchPersonIdException(id);
		}

	/**
	* @return the name of a School
	*/

	public String getName() {
		return _name;
	}

	/**
	* @param person
	* Add person to a school 
	*/

	void addPerson(Person person) {
		_people.put(person.getId(),person);
	}


	/**
	* @param course	
	* Add course to a school 
	*/

	void addCourse(Course course) {
		_courses.put(course.getName(),course);
	}

	/**
	* @return Collection type Person
	* Need to be sort here!!
	*/

	List<Person> getAllUsers() {
			List<Person> people = new ArrayList<>(_people.values());
		return people;
	}

		/**
		* @param filename
		* @throws BadEntryException
		* @throws IOException
		*/

	public void importFile(String filename) throws IOException, BadEntryException, ImportFileException  {
		Parser parser = new Parser(this);
		parser.parseFile(filename);
	}

		/** O método parseCourse(String course) de School tem como 
		* responsabilidade devolver o curso com o nome dado. 
		* Caso ainda não exista um curso com o nome dado, este método 
		* cria um curso com o nome dado, guarda o curso na universidade
	* @param curso
	* @return curso 
		*/
		
	public Course parseCourse(String curso) {
		if (_courses.containsKey(curso))
			return _courses.get(curso);
		else {
			Course nCurso = new Course(curso);
			this.addCourse(nCurso);
			return nCurso;
		}
	}
  
  public Course getCourse(String course) throws NoSuchCourseIdException {
    if (_courses.containsKey(course) ){
      return _courses.get(course);
    } else throw new NoSuchCourseIdException(course);
  }
}
