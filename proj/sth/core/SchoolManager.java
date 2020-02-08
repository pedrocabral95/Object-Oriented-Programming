package sth.core;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSurveyIdException;
import sth.core.exception.NonEmptySurveyIdException;
import sth.core.exception.SurveyFinishedIdException;
import sth.core.exception.FinishingSurveyIdException;
import sth.core.exception.ClosingSurveyIdException;
import sth.core.exception.OpeningSurveyIdException;
import sth.core.exception.DuplicateSurveyIdException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchSurveyIdException;
import sth.core.exception.NoSuchCourseIdException;

import pt.tecnico.po.ui.DialogException;

/** Importing core classes */
import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Employee;
import sth.core.Course;
import sth.core.Student;
import sth.core.Teacher;
import sth.core.Discipline;


/** Importing Comparators */
import sth.core.DisciplineComparator;
import sth.core.CourseComparator;
import sth.core.NameComparator;
import sth.core.IdComparator;


/** Importing java libraries */
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.io.Serializable;
import java.util.zip.*;
import java.util.Comparator;
import java.io.*;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
;
/**
 * The facade class.
 */
public class SchoolManager implements Serializable { 

	/** School associated to a SchoolManager */
	private School _school;

	/** Person logged */
	private Person _loggedPerson;

	/** File name to named a file */
	private String _fileName = null;

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	
	/**
	*   Class SchoolManager Construtor
	*/
	
	public SchoolManager() {
		_school = new School("Tecnico");
	}
	
	/**
	 * @param datafile
	 * @throws ImportFileException
	 * @throws BadEntryException
	 */

	public void importFile(String datafile) throws ImportFileException {
		try {
			_school.importFile(datafile);

		} catch (IOException | BadEntryException e) {
			throw new ImportFileException(e);
		}
	}

	/**
	 * @param filename
	 * @throws IOException
	 * @throws FileNotFoundException
	 */

	public void loadFile(String filename) throws FileNotFoundException, ClassNotFoundException, NoSuchPersonIdException,IOException {
		
		FileInputStream file = new FileInputStream(filename);
		ObjectInputStream inp = new ObjectInputStream(file);
		School nova = (School) inp.readObject();
		_loggedPerson = nova.getPerson(_loggedPerson.getId());
		_school = nova;
		_fileName = filename;
		inp.close();
		file.close();
	}

	 /**
	 * @throws IOException
	 * @throws FileNotFoundException
	 */

	public void saveFile() throws FileNotFoundException, IOException {

		FileOutputStream novofile = new FileOutputStream(_fileName);
		ObjectOutputStream outp = new ObjectOutputStream(novofile);
		outp.writeObject(_school);
		outp.close();
		novofile.close();
	}

	/**
	* Changes the associated file name with a new name
	*/
	public void setFileName(String fileName){
		_fileName = fileName;
	}

	/**
	* Do the login of the user with the given identifier.
	* @param id identifier of the user to login
	* @throws NoSuchPersonIdException if there is no users with the given identifier
	*/

	public void login(int id) throws NoSuchPersonIdException {
		_loggedPerson = _school.getPerson(id);
	}

	/**
	* @return loggedUser
	*/

	public Person getLoggedUser() {
		return _loggedPerson;
	}

	/**
	*	This method change a phone number of logged user! 
	*   @param number
	*/

	public void changeNumber(int number) {
		_loggedPerson.setPhone(number);
	}

	/**
	* @param person
	* @return List<String> which contains every person who contains a string %person%
	*/

	public List<String> searchPerson(String person) {
		List<Person> getPersons = new ArrayList<Person>();  
		List<String> output = new ArrayList<String>();

		//Choose people with the required name

		for (Person p:_school.getAllUsers()) {
			if (p.getName().contains(person))
				getPersons.add(p);
		}

		//Sort them alphabetically

		Collections.sort(getPersons, new NameComparator());

		//Each person's details

		for (Person p : getPersons) { 
			output.add(p.toString());

			if (p instanceof Teacher) {
				Teacher t = (Teacher) p;
				List<Discipline> listDisciplines = new ArrayList<Discipline>(t.getDisciplines());
				Collections.sort(listDisciplines, new DisciplineComparator());
				for (Discipline d : listDisciplines)
					output.add(d.toString());
			}
			if (p instanceof Employee) {
				Employee t = (Employee) p;
				if (t.getCourse() == null || t.getCourse().getDisciplines().size() == 0)
					continue;
				List<Discipline> listDisciplines = new ArrayList<Discipline>(t.getCourse().getDisciplines());
				Collections.sort(listDisciplines, new DisciplineComparator());
				for (Discipline d : listDisciplines)
					output.add(d.toString());
			}
			if (p instanceof Student) {
				Student s = (Student) p;
				List<Discipline> listDisciplines = new ArrayList<Discipline>(s.getCourse().getDisciplines());
				Collections.sort(listDisciplines, new DisciplineComparator());
				for (Discipline d : listDisciplines) {
					if (d.areYouEnrolled(s))
						output.add(d.toString());
				}
			}
		}
		return output;
	}

	/**
	* @return List<String> which contains all persons and his informations
	*/

	public List<String> showAllPersons() {
		List<Person> getPersons = _school.getAllUsers();  
		List<String> output = new ArrayList<String>();

		//Sort by Id Number
		Collections.sort(getPersons, new IdComparator());

		//Each person's details
		for (Person p : getPersons) { 
			//Add person p to the final List  
			output.add(p.toString());
			if (p instanceof Teacher) {
				Teacher t = (Teacher) p;
				List<Discipline> listDisciplines = new ArrayList<Discipline>(t.getDisciplines());
				Collections.sort(listDisciplines, new DisciplineComparator());
				for (Discipline d : listDisciplines)
					output.add(d.toString());
			}
			if (p instanceof Employee) {
				Employee t = (Employee) p;
				if (t.getCourse() == null || t.getCourse().getDisciplines().size() == 0)
					continue;
				List<Discipline> listDisciplines = new ArrayList<Discipline>(t.getCourse().getDisciplines());
				Collections.sort(listDisciplines, new DisciplineComparator());
				for (Discipline d : listDisciplines)
					output.add(d.toString());
			}
			if (p instanceof Student) {
				Student s = (Student) p;
				List<Discipline> listDisciplines = new ArrayList<Discipline>(s.getCourse().getDisciplines());
				Collections.sort(listDisciplines, new DisciplineComparator());
				for (Discipline d : listDisciplines) {
					if (d.areYouEnrolled(s))
						output.add(d.toString());
				}
			}
		}
		return output;
	}

	/**
	* @return List<String> which contains a person and his information
	*/

	public List<String> showPerson(){
		List<String> output = new ArrayList<String>();
		_loggedPerson.showPerson(output);
		return output;
	}

	/**
	* @return true when the currently logged in person is an administrative
	*/

	public boolean isLoggedUserAdministrative() throws NoSuchPersonIdException {
		return (_loggedPerson  instanceof Employee); 		
	}

	/**
	* @return true when the currently logged in person is a professor
	*/

	public boolean isLoggedUserProfessor() throws NoSuchPersonIdException {
		return (_loggedPerson instanceof Teacher);
	}

	/**
	* @return true when the currently logged in person is a student
	*/

	public boolean isLoggedUserStudent() throws NoSuchPersonIdException {
		return (_loggedPerson instanceof Student);
	}

	/**
	* @return true when the currently logged in person is a representative
	*/

	public boolean isLoggedUserRepresentative() throws NoSuchPersonIdException {
		if (isLoggedUserStudent()) {
			Student p = (Student) _loggedPerson;
			if (p.isRepresentative()) 
				return true;
		}
		return false;
	}

	/**
	* @return filename 
	*/

	public String getFile() {
		return _fileName;
	}

	/**
	* Read Messages when doing login
	*/

	public ArrayList<String> readMessage(){
		return _loggedPerson.readMessage();
	}

	/**
	* Clear messages reads
	*
	*/

	public void clearMessage() {
		_loggedPerson.clearMessage();
	}

	/**
	* @param discipline
	* @param project
	* @return List<String> which contains all results for a certain survey
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	* @throws NoSurveyIdException
	*/

	public List<String> showSurveyResults(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException {
		if (_loggedPerson instanceof Student) {
			Student s = (Student) _loggedPerson;
			return s.showSurveyResults(discipline,project);
		}
		else {
			Teacher t = (Teacher) _loggedPerson;
			return t.showSurveyResults(discipline,project);
		}
	}

	/**
	* Submits a project to a certain Discipline
	* @param discipline
	* @param project
	* @param description
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public void submitProject(String discipline, String project, String description) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		Student s = (Student) _loggedPerson;
		s.submitProject(discipline,project,description);
	}

	/**
	* Cancels a survey associated with a project
	* @param discipline
	* @param project
	* @throws NoSurveyIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public void cancelSurvey(String discipline, String project) throws SurveyFinishedIdException, NonEmptySurveyIdException, NoSurveyIdException, NoSuchDisciplineIdException, NoSuchProjectIdException {
		Student s = (Student) _loggedPerson;
		s.cancelSurvey(discipline,project);
	}

	/**
	* Closes a survey associated with a project
	* @param discipline
	* @param project
	* @throws NoSurveyIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	* @throws ClosingSurveyIdException
	*/

	public void closeSurvey(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException, ClosingSurveyIdException {
		Student s = (Student) _loggedPerson;
		s.closeSurvey(discipline,project);
	}

	/**
	* Submits a survey associated with a project
	* @param discipline
	* @param project
	* @param hours
	* @param description
	* @throws NoSurveyIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public void submitSurvey(String discipline, String project, int hours, String description) throws NoSuchProjectIdException, NoSuchDisciplineIdException , NoSurveyIdException, NoSuchSurveyIdException{
		Student s = (Student) _loggedPerson;
		s.submitSurvey(discipline,project,hours,description);
	}

	/**
	* Finalizes a survey associated with a project
	* @param discipline
	* @param project
	* @throws NoSurveyIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	* @throws FinishingSurveyIdException
	*/

	public void finalizeSurvey(String discipline, String project) throws NoSuchDisciplineIdException , NoSuchProjectIdException , NoSurveyIdException, FinishingSurveyIdException {
		Student s = (Student) _loggedPerson;
		s.finalizeSurvey(discipline,project);
	}

	/**
	* Opens a survey associated with a project
	* @param discipline
	* @param project
	* @throws NoSurveyIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	* @throws OpeningSurveyIdException
	*/

	public void openSurvey(String discipline, String project) throws OpeningSurveyIdException, NoSuchDisciplineIdException, NoSuchProjectIdException,NoSurveyIdException{
		Student s = (Student) _loggedPerson;
		s.openSurvey(discipline,project);
	}

	/**
	* Creates a survey associated with a project
	* @param discipline
	* @param project
	* @throws DuplicateSurveyIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public void createSurvey(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException, DuplicateSurveyIdException{
		Student s = (Student) _loggedPerson;
		s.createSurvey(discipline,project);
	}

	/**
	* Shows the survey results to a representative
	* @param discipline
	* @throws NoSurveyIdException
	* @throws NoSuchDisciplineIdException
	*/

	public List<String> showSurveyResultsRep(String discipline) throws NoSuchDisciplineIdException, NoSurveyIdException {
		Student s = (Student) _loggedPerson;
		return s.showSurveyResultsRep(discipline);
	}

	/**
	TEACHER
	*/

	/**
	* Creates a project associated with a discipline
	* @param discipline
	* @param name
	* @throws DuplicateProjectIdException
	* @throws NoSuchDisciplineIdException
	*/

	public void createProject(String discipline, String name) throws NoSuchDisciplineIdException, DuplicateProjectIdException{
		Teacher t = (Teacher) _loggedPerson;
		t.createProject(discipline,name);
	}

	/**
	* Closes a project associated with a discipline
	* @param discipline
	* @param name
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public void closeProject(String discipline, String name) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		Teacher t = (Teacher) _loggedPerson;
		t.closeProject(discipline,name);
	}

	/**
	* @param discipline
	* @param project
	* @return List<String> with all the project submissions for a certain project
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public List<String> getProjectSubmissions(String discipline, String project) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		Teacher t = (Teacher) _loggedPerson;
		return t.getProjectSubmissions(discipline,project);
	}

  public void removeStudent(String discipline,int id) throws NoSuchDisciplineIdException, NoSuchPersonIdException {
    Teacher t = (Teacher) _loggedPerson;
    t.removeStudent(discipline,id);
  }

  public void makeRepresentative(String course, int id) throws NoSuchCourseIdException, NoSuchPersonIdException {
    _school.getCourse(course).makeRepresentative(id);
  }
	/**
	* @param discipline
	* @return List<String> with all students of a certain discipline
	* @throws DuplicateProjectIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSuchProjectIdException
	*/

	public List<String> getStudentsOfDiscipline(String discipline) throws NoSuchDisciplineIdException {
		Teacher t = (Teacher) _loggedPerson;
		
		List<Student> getStudents = t.getStudentsOfDiscipline(discipline); 
		List<String> output = new ArrayList<String>();

		//Sort by Id Number
		Collections.sort(getStudents, new IdStudentComparator());

		//Each student's details
		for (Student s : getStudents) { 
			//Add person p to the final list 
			output.add(s.toString());
			List<Discipline> listDisciplines = new ArrayList<Discipline>(s.getCourse().getDisciplines());
			Collections.sort(listDisciplines, new DisciplineComparator());
			for (Discipline d : listDisciplines) {
				if (d.areYouEnrolled(s))
				output.add(d.toString());
			}
		}
		return output;
	}

  public List<String> showInfo() {

    Teacher t = (Teacher) _loggedPerson;
    
    List<Discipline> getDisciplines = t.getDisciplines(); 
    List<String> output = new ArrayList<String>();

    //Sort by Id Number
    Collections.sort(getDisciplines, new DisciplineComparator());

    for (Discipline d : getDisciplines) {
      output.add("Numero de projectos " + d.getNumberProjects());
      output.add("Numero de Submissoes " + d.getNumberSub());
    }
    return output;
  }
    
}