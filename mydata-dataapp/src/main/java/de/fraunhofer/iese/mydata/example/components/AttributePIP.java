package de.fraunhofer.iese.mydata.example.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.fraunhofer.iese.mydata.pip.PipService;
import de.fraunhofer.iese.mydata.registry.ActionDescription;
import de.fraunhofer.iese.mydata.registry.ActionParameterDescription;
import de.fraunhofer.iese.mydata.component.health.HealthStatus;
import de.fraunhofer.iese.mydata.component.health.Status;

@PipService(componentName = "attribute")
public class AttributePIP {
    private static final Logger LOG = LoggerFactory.getLogger(AttributePIP.class);

    @ActionDescription(methodName = "checkAttribute")
    public boolean checkAttribute(@ActionParameterDescription(name = "attribute-name") String attributeName,
            @ActionParameterDescription(name = "attribute-value") String attributeValue) {
        final String currentAttributeName = "age"; // hardcoded for now
        final String currentAttributeValue = "18"; // hardcoded for now
        LOG.debug("checkAttribute pip called, returning: {}", currentAttributeName);
        return !currentAttributeName.equalsIgnoreCase(attributeName)
                || currentAttributeValue.equalsIgnoreCase(attributeValue);
    }

    @ActionDescription(methodName = "connector")
    public boolean checkConnector(
            @ActionParameterDescription(name = "connector-uri2") String connectorURI1,
            @ActionParameterDescription(name = "connector-uri1") String connectorURI2,
            @ActionParameterDescription(name = "operator") String operator) {
        String current_connector_uri = "http://localhost:8080/connector1"; // hardcoded for now
        Boolean result = current_connector_uri.equalsIgnoreCase(connectorURI1)
                || current_connector_uri.equalsIgnoreCase(connectorURI2);
        LOG.debug("checkConnector pip called, returning: {}", result);
        return result;
    }

    public HealthStatus getHealth() {
        return HealthStatus.of(Status.UP);
    }
}
