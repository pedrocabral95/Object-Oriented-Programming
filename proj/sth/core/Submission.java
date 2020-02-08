package sth.core;

import sth.core.Parser;
import sth.core.Project;
import sth.core.Student;


public class Submission implements java.io.Serializable {

  // Message associated with submisssion
	private String _message;

  // Project associated with submission
	private Project _project;

  // Student associated with submission
  private Student _student;

  /**
  * @param message
  * @param project
  * @param student
  * Class Submission Constructor
  */

	public Submission(String message , Project project, Student student) {
    _message = message;
    _project = project;
    _student = student;
	}

  /**
  * @return associated student id
  */

	int getStudentId() {
    return _student.getId();
  }

  /**
  * @return String submission type
  *         Submission String is returned
  **/

  public String toString() {
    return  "* " + this.getStudentId() + " - " + _message;
  }
}