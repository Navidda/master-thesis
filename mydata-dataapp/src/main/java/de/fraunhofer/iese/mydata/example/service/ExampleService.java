
package de.fraunhofer.iese.mydata.example.service;

import de.fraunhofer.iese.mydata.example.components.ExamplePEP;
import de.fraunhofer.iese.mydata.example.model.Person;
import de.fraunhofer.iese.mydata.policy.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

  @Autowired
  ExamplePEP examplePep;

  /**
   *
   * @param name
   * @param surName
   * @param age
   * @param city
   * @return a Persons object with given values
   */
  public Person createPerson(String name, String surName, int age, String city) {
    final Person person = new Person();
    person.setName(name);
    person.setSurName(surName);
    person.setAge(age);
    person.setCity(city);

    return person;
  }

  /**
   * Method call with ExamplePep enforcement for a Person object
   *
   * @param person
   * @return returns the enforcedPerson
   */
  public Person getMydataPerson(Person person) {
    final Event event = this.examplePep.enforcePerson(person).toBlocking().first();
    return event.getParameterValue("person", person.getClass());
  }

  /**
   * Method call with ExamplePep enforcement for a String object
   *
   * @param s
   * @return returns the enforcedString
   */
  public String getMydataString(String s) {
    final Event event = this.examplePep.enforceString(s).toBlocking().first();
    return event.getParameterValue("string", s.getClass());
  }

  /**
   * Method call with ExamplePep enforcement for an object
   *
   * @param object
   * @return returns the enforcedObject
   */
  public Object getMydataObject(Object object) {
    final Event event = this.examplePep.enforceObject(object).toBlocking().first();
    return event.getParameterValue("object", object.getClass());
  }

}
