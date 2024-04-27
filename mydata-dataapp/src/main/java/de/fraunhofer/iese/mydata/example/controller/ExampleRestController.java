/*******************************************************************************
 * Copyright (C) 2016 Fraunhofer IESE (www.iese.fraunhofer.de).
 *
 * <p>Licensed under the Terms and Conditions on Licensing Software for Permanent
 * and Gratuitously Use of Fraunhofer-Gesellschaft zur Förderung der angewandten
 * Forschung e.V., Munich.
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at</p>
 *
 * <p>http://s.fhg.de/ind2uce-sw-licence</p>
 *
 * <p>Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.</p>
 *******************************************************************************/

package de.fraunhofer.iese.mydata.example.controller;

import de.fraunhofer.iese.mydata.example.model.Person;
import de.fraunhofer.iese.mydata.example.service.ExampleService;
import de.fraunhofer.iese.mydata.policy.exception.InhibitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest")
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExampleRestController {

  @Autowired
  ExampleService service;

  @PostMapping("/person")
  public ResponseEntity<?> enforcePerson(@RequestParam("name") String name, @RequestParam("surName") String surName, @RequestParam("age") int age, @RequestParam("city") String city,
      HttpServletResponse response) {
    Person person = this.service.createPerson(name, surName, age, city);
    person = this.service.getMydataPerson(person);

    if (person == null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString().concat(" Person is null"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(person, HttpStatus.OK);
  }

  @GetMapping("/test")
  public ResponseEntity<?> getTest(HttpServletResponse response) {
    final Person person = this.service.createPerson("aName", "aFamName", -1, "aPlace");
    if (person == null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString().concat(" Person is null"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(person, HttpStatus.OK);
  }

  @PostMapping("/object")
  public ResponseEntity<?> enforceObject(@RequestBody Object obj, HttpServletResponse response) {
    obj = this.service.getMydataObject(obj);
    if (obj == null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString().concat(" String is null"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(obj, HttpStatus.OK);
  }

  @PostMapping("/string")
  public ResponseEntity<?> enforceString(@RequestParam("string") String s, HttpServletResponse response) {
    s = this.service.getMydataString(s);

    if (s == null) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR.toString().concat(" String is null"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<>(s, HttpStatus.OK);
  }

  /*
   * Handle InhibitException in RuntimeException
   */
  @ExceptionHandler({
      RuntimeException.class
  })
  public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
    if (e.getCause() instanceof InhibitException) {
      return this.error(HttpStatus.FORBIDDEN, (InhibitException)e.getCause());
    }
    return this.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
  }

  private ResponseEntity<String> error(HttpStatus status, Exception e) {
    return ResponseEntity.status(status).body(e.getMessage());
  }

}
