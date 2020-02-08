package sth.app.exception;

import pt.tecnico.po.ui.DialogException;

/**
 *
 */
public class NoSuchCourseException extends Exception {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;
  
  private String _course;
  private int _id;
  /**
   * @param discipline 
   * @param project 
   */
  public NoSuchCourseException(String course, int id) {
    _course = course;
    _id = id;
  }

  /** @see pt.tecnico.po.ui.DialogException#getMessage() */
  @Override
  public String getMessage() {
    return Message.noCourse(_course, _id);
  }

}
