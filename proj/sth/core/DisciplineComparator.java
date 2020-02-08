package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Discipline;

/**  
*  This class creates a comparator that compares disciplines by their alphabetic order
*/

public class DisciplineComparator implements Comparator<Discipline>, java.io.Serializable{
	@Override
	public int compare(Discipline a, Discipline b){
    if (a.getCourse().getName().compareTo(b.getCourse().getName()) == 0)
      return a.getName().compareTo(b.getName());
    return a.getCourse().getName().compareTo(b.getCourse().getName()); 
	}
}