package sth.core;

import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Course;
import sth.core.Student;
import sth.core.Discipline;
import sth.core.Project;
import sth.core.Survey;
import sth.core.CourseComparator;
import sth.core.DisciplineComparator;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.DuplicateProjectIdException;
import sth.core.exception.NoSuchProjectIdException;
import java.io.IOException;



public class Teacher extends Person implements java.io.Serializable {

	// Disciplines associated with teacher
	private HashMap<String,Discipline> _disciplines;

  private List<Discipline> _output = new ArrayList<Discipline>();
 
	//Serial number for serialization.
	private static final long serialVersionUID = 201810051538L;
		
	/**
  	* @param id
  	* @param phone
  	* @param name
  	* Class Teacher Construtor
  	*/

	public Teacher(int id, int phone,String name) {
		super(id,phone,name);
		_disciplines = new HashMap<String,Discipline>();
	}

	/**
	*	@param discipline 
	*	Adds a discipline to the associated disciplines
	*/

	void addDiscipline(Discipline discipline) {
    if (_disciplines.containsKey(discipline))
      if (_disciplines.get(discipline).getCourse().getName().equals(discipline.getCourse().getName())) {
        _output.add(discipline);
        return;
      }
    else {
		_disciplines.put(discipline.getName(),discipline);
    }
	}

	/**
	* @return associated disciplines
	*/

	List<Discipline> getDisciplines() {
		List<Discipline> output = new ArrayList<Discipline>(_disciplines.values());
    for(Discipline d : output)
      _output.add(d);
		return _output;
	}

	/**
  	* @param output
  	* @return List<String> with all associated disciplines
  	*/

	@Override
	public List<String> addDisciplines(List<String> output){
		List<Discipline> listDisciplines = new ArrayList<Discipline>(getDisciplines());
		Collections.sort(listDisciplines, new DisciplineComparator());
		for (Discipline d : listDisciplines)
			output.add(d.toString());
		return output;
	}


  public void removeStudent(String discipline, int id) throws NoSuchDisciplineIdException, NoSuchPersonIdException {
    if (_disciplines.containsKey(discipline)) {
      Discipline dis = _disciplines.get(discipline);
      dis.removeStudent(id);
    } else throw new NoSuchDisciplineIdException(discipline); // nao leciona, lança erro
  }
	/**
	* 4.4.1 Create a Project.
	* @param discipline
	* @param name
	* @throws NoSuchDisciplineException
	* @throws DuplicateProjectException
	* 
	* This method create aproject.
	* if the teacher's teaching and if the project does not exists!
	* Throws exceptions if one of them it's not true!
	*/

	void createProject(String discipline, String name) throws NoSuchDisciplineIdException, DuplicateProjectIdException{ 
		if (_disciplines.containsKey(discipline)) {
			Discipline dis = _disciplines.get(discipline);
			dis.createProject(name);
		} else throw new NoSuchDisciplineIdException(discipline); // nao leciona, lança erro
	}

	/**
	* 4.4.2 Close a Project.
	* @param discipline
	* @param name
	* @throws NoSuchDisciplineException
	* @throws DuplicateProjectException
	* 
	* This method close a project if the teacher's teaching and if the project exists!
	* Throws exceptions if one of them it's not true!
	*/

	void closeProject(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		if (_disciplines.containsKey(discipline)) {
			Discipline dis = _disciplines.get(discipline);
			if (dis.getProject(project).isClosed())
				return;
			dis.getProject(project).close();
		} else throw new NoSuchDisciplineIdException(discipline); // nao leciona, lança erro
	}

	/**
	* 4.4.3 Get Project Submissions
	* @param discipline
	* @param project
	* @throws NoSuchDisciplineException
	* @throws NoSuchProjectException
	*
	*/

	List<String> getProjectSubmissions(String discipline, String project) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		
		if (_disciplines.containsKey(discipline)) {
			List<String> output = new ArrayList<String>();
			Project proj = _disciplines.get(discipline).getProject(project);
			output.add(discipline + " - " + proj.getName());
			for (Submission s : proj.getProjectSubmissions()) {
				output.add(s.toString());
			}
			return output;
		} else throw new NoSuchDisciplineIdException(discipline); // nao leciona, lança erro
	}

	/**
	* 4.4.4 Get Discipline Students
	* @param discipline
	* @throws NoSuchDisciplineException
	*
	*/

	List<Student> getStudentsOfDiscipline(String discipline) throws NoSuchDisciplineIdException {
		if (_disciplines.containsKey(discipline)) {
				return _disciplines.get(discipline).getAllStudents();
		} else throw new NoSuchDisciplineIdException(discipline); // nao leciona, lança erro
	}
	
	/**
	* 4.4.5 Show Survey Results. 
	* @param discipline
	* @param project
	* @throws NoSuchProjectIdException
	* @throws NoSuchDisciplineIdException
	*       This method show a discipline project Survey Results 
	*/

	List<String> showSurveyResults(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException {
		List<String> output = new ArrayList<String>();
		if (_disciplines.containsKey(discipline)) {
			Project p = _disciplines.get(discipline).getProject(project);
			Survey s = p.getSurvey();
			if (!(s == null)) {
			output.add(s.toString());
			if (s.isFinalized()) {
				output.add(" * Número de submissões: " + p.getSubmissions());
				output.add(" * Número de respostas: " + s.getAnswers());
				output.add(" * Tempos de Resolução (horas) (mínimo, médio, máximo): " + s.getMin() + ", " + s.getMedia() + ", " + s.getMax());
				return output;
			}
			return output;
			} else throw new NoSuchProjectIdException(project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	* @return String teacher type
	*         Teacher String is returned
	**/

	@Override
	public String toString() {
		return "DOCENTE|" + this.getId() + "|" + this.getPhone() + "|"+ this.getName();
	}

	/**
	* Parses the context information for a person from the import file.
	* This method defines the default behavior: no extra information is needed
	* thus it throws the exception.
	**/
	
	@Override
	public void parseContext(String lineContext, School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|");

		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);

		Course course = school.parseCourse(components[0]);
		Discipline discipline = course.parseDiscipline(components[1]);
		discipline.addTeacher(this);
		_disciplines.put(discipline.getName(),discipline);
	}


}
