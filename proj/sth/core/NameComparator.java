package sth.core;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import sth.core.Person;

/**  
*  This class creates a comparator that compares people by their names's alphabetic order
*/

public class NameComparator implements Comparator<Person>, java.io.Serializable{
  @Override
  public int compare(Person a, Person b){
    if (a.getName().compareTo(b.getName()) <= -1) {
      return -1;
    } if (a.getName().compareTo(b.getName()) >= 1) {
        return 1;
    } else { //marca igual
        return a.getName().compareTo(b.getName());
    }
  //0 sao iguais
  //-1 quer dizer que tem de vir antes na lista
  //1 quer dizer que tem de vir depois na lista
  }
}