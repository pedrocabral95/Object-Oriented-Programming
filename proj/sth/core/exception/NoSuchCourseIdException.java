package sth.core.exception;

/** Exception thrown when the requested person does not exist. */
public class NoSuchCourseIdException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201809021324L;

  /** Course id. */
  private String _course;

  /**
   * @param id
   */
  public NoSuchCourseIdException(String course) {
    _course = course;
  }

 
  public String getCourse() {
    return _course;
  }

}
