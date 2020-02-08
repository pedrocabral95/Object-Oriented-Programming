package sth.core;

import sth.core.Parser;
import sth.core.School;
import sth.core.Person;
import sth.core.Course;
import sth.core.Student;
import sth.core.Discipline;
import sth.core.Observer;
import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;

import java.io.IOException;

public abstract class Person implements Serializable, Observer {
  
  // Id associated with the person 
	private int _id;

  // Name of the person 
	private String _name;

  // Phone number associated with the person 
	private int _phone;

	// Serial number for serialization.
  private static final long serialVersionUID = 201810051538L;

  // Notes associated with person
  private ArrayList<String> _notes;

  /**
  *   @param id
  *		@param phone
  *		@param name
  *   Class Person Construtor
  */

	public Person(int id, int phone, String name) {
		_id = id;
		_phone = phone;
		_name = name;
		_notes = new ArrayList<String>();
  }

  /**
  * @return person's name
  */

	public String getName() {
		return _name;
	}

  /**
  * @return associated id
  */

	public int getId() {
		return _id;
	}

  /**
  * @return associated phone number
  */

	public int getPhone() {
		return _phone;
	}

  /**
  * Adds a notification
  */ 

  public void update(String note) {
    //System.out.println(_notes.size() + " len before update");
    //System.out.println(_notes + " before");
    _notes.add(note);
    //System.out.println(_notes + " atfer");
    //System.out.println(_notes.size() + " len after update");
  }

  /**
  * @return all notifications
  */

  public ArrayList<String> readMessage() {
    //System.out.println(_notes.size() +" read person");
    return _notes;
  }

  /**
  * Cleares all notifications
  */

  public void clearMessage() {
    _notes.clear();
  }


  //Adiciona o utilizador ao output
  public List<String> addPerson(List<String> output){

    output.add(toString());

    return output;
  }

  //Adiciona as disciplinas ao output

  public abstract List<String> addDisciplines(List<String> output);

  /**
   * @return List<String> which contains a person and his information
   */

  public List<String> showPerson(List<String> output){

    addPerson(output);
    addDisciplines(output);

    return output;
  }

	public abstract String toString();

	public void setPhone(int phone) {
		_phone = phone;
	}
	 /**
   * Parses the context information for a person from the import file.
   * This method defines the default behavior: no extra information is needed
   * thus it throws the exception.
   **/
	public void parseContext(String context, School school) throws BadEntryException  {
    	throw new BadEntryException("Should not have extra context: " + context);
 	}
  	
  
}