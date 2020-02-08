package sth.core.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *   Class for Opening Survey throws an error.
 */
public class OpeningSurveyIdException extends Exception {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 201810051538L;
	
	private String _discipline;
	private String _project;
	/**
	 * @param discipline 
	 * @param project 
	 */
	public OpeningSurveyIdException(String discipline, String project) {
		_discipline = discipline;
		_project = project;
	}
	/**
	* @return String discipline
	*/
	public String getDiscipline() {
		return _discipline;
	}
	/**
	* @return String project
	*/
	public String getProject() {
		return _project;
	}

}
