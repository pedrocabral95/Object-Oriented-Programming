package sth.core;

import sth.app.App;
import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Course;
import sth.core.Student;
import sth.core.Discipline;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;

public class Employee extends Person implements java.io.Serializable{

  // Course associated with the employee
  private Course _course;

  // Disciplines associated with the employee
  private HashMap<String,Discipline> _disciplines;

  //Serial number for serialization.
  private static final long serialVersionUID = 201810051538L;

  /**
  * @param id
  * @param phone
  * @param name
  * Class Employee Constructor
  */

  public Employee(int id, int phone,String name) {
      super(id,phone,name);
      _disciplines = new HashMap<String,Discipline>();
  }

  /**
  * @param output
  * @return List<String> with all associated disciplines
  */

  @Override
  public List<String> addDisciplines(List<String> output){
    
    if (getCourse() == null || getCourse().getDisciplines().size() == 0)
      return output;
    
    List<Discipline> listDisciplines = new ArrayList<Discipline>(getCourse().getDisciplines());
    Collections.sort(listDisciplines, new DisciplineComparator());
    
    for (Discipline d : listDisciplines)
      output.add(d.toString());
    
    return output;
  }

  /**
  * @return String employee type
  *         Employee String is returned
  **/

  @Override
  public String toString() {
    return "FUNCIONÁRIO|" + this.getId() + "|" + this.getPhone() + "|" + this.getName();
  }

  /**
  * @return associated course
  */

  Course getCourse() {
    return _course;
  }
}

