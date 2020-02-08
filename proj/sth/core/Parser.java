package sth.core;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.Reader;
import sth.core.School;
import sth.core.Person;
import sth.core.Discipline;
import sth.core.Student;
import sth.core.Employee;
import sth.core.Course;
import sth.core.Teacher;
import java.util.Collection;
import java.util.ArrayList;

import sth.core.exception.BadEntryException;

public class Parser implements java.io.Serializable{

  // School associated with the parser
  private School _school;

  // Person associated with the parser
  private Person _person;

  /**
  * @param s
  * Class Parser Constructor
  */

  public Parser(School s) {
    _school = s;
  }

  /**
  * @param filename
  * Parses a certain file
  */

  public void parseFile(String fileName) throws IOException, BadEntryException {
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = reader.readLine()) != null)
        parseLine(line);
    }
  }

  /**
  * @param line
  * Parses a certain line
  */

  private void parseLine(String line) throws BadEntryException {
    if (line.startsWith("#"))
      parseContext(line);
    else
      parseHeaderPerson(line);
  }

  /**
  * @param header
  * Parses a header for a person
  */

  private void parseHeaderPerson(String header) throws BadEntryException {
    String[] components = header.split("\\|");
    int id;
    int phone;

    if (components.length != 4)
      throw new BadEntryException("Invalid line " + header);

    id = Integer.parseInt(components[1]);
    phone = Integer.parseInt(components[2]);

    switch (components[0]) {
      case "FUNCIONÁRIO":
        _person = new Employee(id, phone, components[3]);
        break;

      case "DOCENTE":
        _person = new Teacher(id, phone, components[3]);
        break;

      case "ALUNO":
        _person = new Student(id, phone, components[3], false);
        break;

      case "DELEGADO":
        _person = new Student(id, phone, components[3], true);
        break;

      default:
        throw new BadEntryException("Invalid token " + components[0] + "in line describing a person");
     }

    _school.addPerson(_person);
  }

  /**
  * @param line
  * Parses a certain line context
  */

  private void parseContext(String line) throws BadEntryException{
    String lineContext = line.substring(2);
    _person.parseContext(lineContext, _school);
  }
}