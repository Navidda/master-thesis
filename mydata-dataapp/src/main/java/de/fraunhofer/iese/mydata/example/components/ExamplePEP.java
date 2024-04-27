
package de.fraunhofer.iese.mydata.example.components;


import de.fraunhofer.iese.mydata.example.model.Person;
import de.fraunhofer.iese.mydata.pep.modifiers.basic.DeleteModifierMethod;
import de.fraunhofer.iese.mydata.pep.modifiers.basic.ReplaceModifierMethod;
import de.fraunhofer.iese.mydata.pep.modifiers.string.AnagramModifierMethod;
import de.fraunhofer.iese.mydata.pep.modifiers.string.AppendModifierMethod;
import de.fraunhofer.iese.mydata.pep.modifiers.string.SubStringModifierMethod;
import de.fraunhofer.iese.mydata.policy.event.Event;
import de.fraunhofer.iese.mydata.reactive.common.EventParameter;
import de.fraunhofer.iese.mydata.reactive.common.EventSpecification;
import de.fraunhofer.iese.mydata.reactive.common.Modifiers;
import de.fraunhofer.iese.mydata.reactive.common.PepServiceDescription;
import rx.Observable;

@PepServiceDescription(componentName = "example-pep")
@Modifiers(classNames = {
        AppendModifierMethod.class, ReplaceModifierMethod.class, DeleteModifierMethod.class, AnagramModifierMethod.class, SubStringModifierMethod.class
})
public interface ExamplePEP {
  @EventSpecification(action = "enforceObject")
  Observable<Event> enforceObject(@EventParameter(name = "object") Object object);

  @EventSpecification(action = "enforceString")
  Observable<Event> enforceString(@EventParameter(name = "string") String s);

  @EventSpecification(action = "enforcePerson")
  Observable<Event> enforcePerson(@EventParameter(name = "person") Person person);

}
