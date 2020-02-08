package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Course;

/**  
*  This class creates a comparator that compares courses by their alphabetic order
*/

public class CourseComparator implements Comparator<Course>,java.io.Serializable {
  @Override
  public int compare(Course a, Course b){
    if (a.getName().compareTo(b.getName()) <= -1) {
      return -1;
    } if (a.getName().compareTo(b.getName()) >= 1) {
        return 1;
      //equals
    } else { 
        return a.getName().compareTo(b.getName());
    }

  }
}