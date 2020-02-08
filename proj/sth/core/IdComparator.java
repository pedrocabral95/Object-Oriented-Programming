package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Person;

/**  
*  This class creates a comparator that compares people by their id
*/

public class IdComparator implements Comparator<Person> ,java.io.Serializable{
  @Override
  public int compare(Person a, Person b){
    return a.getId() - b.getId();
  }
}