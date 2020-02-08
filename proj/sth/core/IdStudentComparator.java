package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Student;

/**  
*  This class creates a comparator that compares students by their id
*/

public class IdStudentComparator implements Comparator<Student> , java.io.Serializable {
  @Override
  public int compare(Student a, Student b){
    return a.getId() - b.getId();
  }
}