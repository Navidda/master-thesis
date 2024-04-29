
package de.fraunhofer.iese.mydata.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;
import de.fraunhofer.iese.mydata.component.health.HealthStatus;
import de.fraunhofer.iese.mydata.component.health.Status;

@PipService(componentName = "country")
public class CountryPIP {
  private static final Logger LOG = LoggerFactory.getLogger(CountryPIP.class);

  @ActionDescription(methodName = "checkCountry")
  public boolean checkCountry(@ActionParameterDescription(name = "country-code") String countryCode) {
    final String currentCountryCode = "DE"; // hardcoded for now
    LOG.debug("checkCountry pip called, returning: {}", currentCountryCode);
    return currentCountryCode.equalsIgnoreCase(countryCode);
  }

  public HealthStatus getHealth() {
    return HealthStatus.of(Status.UP);
  }

}
