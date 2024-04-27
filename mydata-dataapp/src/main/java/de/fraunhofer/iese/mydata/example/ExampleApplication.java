
package de.fraunhofer.iese.mydata.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import de.fraunhofer.iese.mydata.pep.EnablePolicyEnforcementPoint;
import de.fraunhofer.iese.mydata.pip.EnablePolicyInformationPoint;
import de.fraunhofer.iese.mydata.pxp.EnablePolicyExecutionPoint;

@EnablePolicyEnforcementPoint(basePackages = "de.fraunhofer.iese.mydata.example.components")
@EnablePolicyInformationPoint
@EnablePolicyExecutionPoint
@SpringBootApplication
public class ExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ExampleApplication.class, args);
  }
}
