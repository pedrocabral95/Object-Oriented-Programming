package sth.core;

import sth.core.School;
import sth.core.Parser;

import sth.core.Person;
import sth.core.DisciplineComparator;
import sth.core.ProjectComparator;
import sth.core.Survey.Condition;
import sth.core.Survey;

import java.util.Comparator;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


import sth.core.exception.NoSuchPersonIdException;
import sth.core.exception.BadEntryException;

import java.io.IOException;

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

public class Student extends Person implements Serializable {
	
	private boolean _representative;

	private Course _course;

	//Serial number for serialization
	private static final long serialVersionUID = 201810051538L;

	/**
		* @param id
		* @param phone
		* @param name
		* @param representatve
		* Class Student Construtor
		*/

	public Student(int id, int phone,String name, boolean representative) {
		super(id,phone,name);
		_representative = representative;
	}

	/**
	*	@param discipline type 
	*	This method add a discipline to a Course.
	*/

	void addDiscipline(Discipline discipline) {
		_course.addDiscipline(discipline);
	}

	/**
	*	@return boolean value 
	*	Return false if it's not a representative
	*		   true if it's a representative
	*/

	boolean isRepresentative() {
		return _representative;
	}

	/**
	* @param boolean representative
	* 			Set a representative student by boolean representative value
	*/

	void setRepresentative(boolean representative) {
		_representative = representative;
	}

	/**
	* @return Course
	* This method return a student course
	*/

	public Course getCourse() {
		return _course;
	}

		/**
		* @param output
		* @return List<String> with all associated disciplines
		*/

	@Override
	public List<String> addDisciplines(List<String> output) {
		List<Discipline> listDisciplines = new ArrayList<Discipline>(getCourse().getDisciplines());
		Collections.sort(listDisciplines, new DisciplineComparator());
		for (Discipline d : listDisciplines) {
			if (d.areYouEnrolled(this))
				output.add(d.toString());
		}
		return output;
	}


	/**
	* @return String student type
	*         Student String is returned
	**/

	@Override
	public String toString() {
		if (isRepresentative())
			return  "DELEGADO|" + this.getId() + "|" + this.getPhone() + "|"+ this.getName();
		return "ALUNO|" + this.getId() + "|" + this.getPhone()+ "|"+ this.getName();
	}

	/**
	* Parses the context information for a person from the import file.
	* This method defines the default behavior: no extra information is needed
	* thus it throws the exception.
	**/

	@Override
	public void parseContext(String lineContext,School school) throws BadEntryException {
		String components[] =  lineContext.split("\\|");
		if (components.length != 2)
			throw new BadEntryException("Invalid line context " + lineContext);
		if (_course == null) {
			_course = school.parseCourse(components[0]);
			_course.addStudent(this);
		}
		Discipline dis = _course.parseDiscipline(components[1]);
		dis.enrollStudent(this);
	}

	/**
	*
	* 4.5.1. Submit Project. (done)
	*
	* @param discipline
	* @param project
	* @param description
	* @throws NoSuchProjectException
	* @throws NoSuchDisciplineException
	*   This method add a submission to a Project!
	*/

	void submitProject(String discipline, String project, String description) throws NoSuchProjectIdException, NoSuchDisciplineIdException {
		if (_course.containDiscipline(discipline)&& _course.getDiscipline(discipline).areYouEnrolled(this)) {
			Project proj = _course.getDiscipline(discipline).getProject(project);
			if ((!proj.isClosed())) {
				proj.addSubmission(this,description);
			} else throw new NoSuchProjectIdException(project);
			} else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	* 4.5.2 Submit Survey. 
	* @param discipline
	* @param project
	* @param description
	* @param hours
	* @throws NoSuchProjectException
	* @throws NoSuchDisciplineException
	* @throws NoSurveyException
	*   		This method add a submission to a Survey Project!
	*/

	void submitSurvey(String discipline, String project, int hours,  String description) throws NoSuchProjectIdException, NoSuchDisciplineIdException, NoSurveyIdException, NoSuchSurveyIdException {
		if (_course.containDiscipline(discipline) && _course.getDiscipline(discipline).areYouEnrolled(this)) {
			Project proj = _course.getDiscipline(discipline).getProject(project);
			if (proj.isClosed()) {
				if (hours < 0) {
					throw new NoSuchSurveyIdException(discipline,project);
				}
				else {
					Survey s = proj.getSurvey();
					if (s.isOpened()) {
						if (!proj.hadSubmited(this))
							s.addAnswer(hours,description);
						if (proj.hadSubmited(this))
							return;
					} else throw new NoSurveyIdException(discipline,project);
			 	}
			} else throw new NoSuchProjectIdException(project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	* 4.5.3 Show Survey Results. 
	* @param discipline
	* @param project
	* @throws NoSuchProjectIdException
	* @throws NoSuchDisciplineIdException
	* @throws NoSurveyIdException
	*       This method show a discipline project Survey Results 
	*/
	
	List<String> showSurveyResults(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException {
		List<String> output = new ArrayList<String>();
		if (_course.containDiscipline(discipline)) {
				Project p = _course.getDiscipline(discipline).getProject(project);
				if (p != null) { 
						Survey s = p.getSurvey();
						if (s != null) {
							output.add(s.toString());
							if (s.isFinalized()) {
								output.add(" * Número de respostas: " + s.getAnswers());
								output.add(" * Tempos médio (horas): " + s.getMedia());
								return output;
							}
							return output;
					} else throw new NoSurveyIdException(discipline,p.getName());
				} else throw new NoSuchProjectIdException(p.getName());
		} else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	* 4.6.1 Create Survey. (Only for Representatives)
	* @param discipline
	* @param project
	* @param description
	* @param hours
	* @throws NoSuchProjectException
	* @throws NoSuchDisciplineException
	* @throws NoSurveyException
	*   This method creatae the Survey Project!
	*/

	void createSurvey(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException, DuplicateSurveyIdException{
		if (_course.containDiscipline(discipline) && _course.getDiscipline(discipline).canCreate()) {
			if (_course.getDiscipline(discipline).hasProject(project)) { 
				Survey s = _course.getDiscipline(discipline).getProject(project).getSurvey();
				if (s == null) {
					// Create a survey
					//set a survey project
					_course.getDiscipline(discipline).getProject(project).setSurvey(project,_course.getDiscipline(discipline),_course.getDiscipline(discipline).getProject(project));
				} else throw new DuplicateSurveyIdException(discipline,project);
			} else throw new NoSuchProjectIdException(project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	* 4.6.2 Cancel Survey. (Only for Representatives)
	* @param discipline
	* @param project
	* @throws NoSuchProjectException
	* @throws NoSuchDisciplineException
	* @throws NoSurveyException
	*   This method cancel the Survey Project!
	*/

	void cancelSurvey(String discipline, String project) throws NoSuchDisciplineIdException, NoSuchProjectIdException,NoSurveyIdException, NonEmptySurveyIdException, SurveyFinishedIdException { 
		if (_course.containDiscipline(discipline) && isRepresentative()) {
			Project proj = _course.getDiscipline(discipline).getProject(project);
				if (proj.getSurvey().isOpened() || proj.getSurvey().isCreated()) {
					if (!proj.getSurvey().hasAnswers()) {
						if (!proj.getSurvey().isFinalized()) {
							proj.cancelSurvey();
						} else throw new SurveyFinishedIdException(discipline,project);
					} else throw new NonEmptySurveyIdException(discipline,project);
				/*if (proj.getSurvey().isClosed()){
					proj.getSurvey().open();
					return;
				}*/
				} else throw new NoSurveyIdException(discipline,project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}

	/**
	* 4.6.3 Open Survey.  (Only for Representatives)
	* @param discipline
	* @param project
	* @throws NoSuchProjectException
	* @throws NoSuchDisciplineException
	* @throws NoSurveyException
	*   This method close the Survey Project!
	*/

	void openSurvey(String discipline, String project) throws OpeningSurveyIdException, NoSuchDisciplineIdException, NoSuchProjectIdException,NoSurveyIdException{ 
		if (_course.containDiscipline(discipline) && isRepresentative()) {
			Project proj = _course.getDiscipline(discipline).getProject(project);
			if (proj.isClosed()) { 
				Survey s = proj.getSurvey();
				if (!s.isFinalized()){
					if (s.isClosed()) // Is Closed!
						s.open(); 
					if (s.isOpened() || !s.isClosed()) //is opened or not closed
						return;		
				} else throw new OpeningSurveyIdException(discipline,project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}
}

	/**
	* 4.6.4 Close Survey.  (Only for Representatives)
	* @param discipline
	* @param project
	* @throws NoSuchProjectException
	* @throws NoSuchDisciplineException
	* @throws NoSurveyException
	*   This method close the Survey Project!
	*/

	void closeSurvey(String discipline, String project) throws ClosingSurveyIdException, NoSuchDisciplineIdException, NoSuchProjectIdException,NoSurveyIdException { 
		if (_course.containDiscipline(discipline) && isRepresentative()) {
			Project proj = _course.getDiscipline(discipline).getProject(project);
			if (_course.getDiscipline(discipline).hasProject(project) && proj.isClosed()) { 
				if (proj.getSurvey().isOpened() && !proj.getSurvey().isFinalized()){
					if (!proj.getSurvey().isClosed()) // Is Closed!
						proj.getSurvey().close(); 
					else return; //is opened or not closed
				} else throw new ClosingSurveyIdException(discipline,project);
			} else throw new NoSuchProjectIdException(project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}

		/**
		* 4.6.4 Finalize Survey.(Only for Representatives)
		* @param discipline
		* @param project
		* @throws NoSuchProjectException
		* @throws NoSuchDisciplineException
		* @throws NoSurveyException
		*   This method close the Survey Project!
		*/

	void finalizeSurvey(String discipline, String project) throws FinishingSurveyIdException, NoSuchDisciplineIdException, NoSuchProjectIdException, NoSurveyIdException {
		if (_course.containDiscipline(discipline) && isRepresentative()) {
			Project proj = _course.getDiscipline(discipline).getProject(project);
			if (_course.getDiscipline(discipline).hasProject(project) && proj.isClosed()) { 
				Survey s = proj.getSurvey();
				if (s.isClosed()) {
					if (!s.isFinalized())
						s.finalize();
					else return;
				} else throw new FinishingSurveyIdException(discipline,project);
			} else throw new NoSuchProjectIdException(project);
		} else throw new NoSuchDisciplineIdException(discipline);
	}

		/**
		* 4.6.4 Show Survey Results.(Only for Representatives)
		* @param discipline
		* @throws NoSuchProjectIdException
		* @throws NoSuchDisciplineIdException
		* @throws NoSurveyIdException
		*   
		*/

	List<String> showSurveyResultsRep(String discipline) throws NoSuchDisciplineIdException, NoSurveyIdException {
			List<String> output = new ArrayList<String>();
			if (_course.containDiscipline(discipline)) {
					Discipline dis = _course.getDiscipline(discipline);
					List<Project> projetos = new ArrayList<Project>(dis.getProjects());
					Collections.sort(projetos, new ProjectComparator());
					for (Project p :projetos){
						Survey s = p.getSurvey();
						if (s != null) {
								if (s.isOpened()) {
									output.add(s.toString());
									return output;
								} 
								if (s.isClosed()) {
									output.add(s.toString());
									return output;
								}
								if (s.isCreated()){
									output.add(s.toString());
									return output;
								}
								output.add(dis.getName() + " - " + p.getName() + s.getAnswers() + " respostas " + s.getMedia() + " horas");
								return output;
						}
						else throw new NoSurveyIdException(discipline,p.getName());
					}
					return output;
			} else throw new NoSuchDisciplineIdException(discipline);
		}
}
		