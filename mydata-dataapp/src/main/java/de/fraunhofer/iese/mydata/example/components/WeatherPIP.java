
package de.fraunhofer.iese.mydata.example.components;

import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;
import de.fraunhofer.iese.mydata.component.health.HealthStatus;
import de.fraunhofer.iese.mydata.component.health.Status;

@PipService(componentName = "weather")
public class WeatherPIP {

  @ActionDescription(methodName = "get-weather")
  public String spatial(@ActionParameterDescription(name = "city") String city) {
    final int cityNameLenght = city.length();

    if (cityNameLenght % 2 == 0) {
      return "nice";
    }

    return "not nice";
  }

  public HealthStatus getHealth() {
    return HealthStatus.of(Status.UP);
  }

}
