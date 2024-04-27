
package de.fraunhofer.iese.mydata.example.components;

import de.fraunhofer.iese.mydata.pxp.PxpService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;
import de.fraunhofer.iese.mydata.component.health.HealthStatus;
import de.fraunhofer.iese.mydata.component.health.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PxpService(componentName = "local-log")
public class LocalLogPXP {
  private static final Logger LOG = LoggerFactory.getLogger(LocalLogPXP.class);

  @ActionDescription(methodName = "log-message")
  public boolean spatial(@ActionParameterDescription(name = "log-message") String logMessage) {
    LOG.info("PXP log message: " + logMessage);

    return true;
  }

  public HealthStatus getHealth() {
    return HealthStatus.of(Status.UP);
  }

}
