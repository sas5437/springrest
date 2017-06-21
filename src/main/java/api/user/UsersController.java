package api.user;

import com.eclipsesource.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;

import api.user.User;
import api.user.UserRepository;
import api.common.exceptions.NullAttributeException;
import api.common.exceptions.BlankAttributeException;
import api.common.exceptions.InvalidAttributeException;
import api.common.exceptions.AttributeException;

@Controller
public class UsersController {

  @Autowired
  private UserRepository userRepository;

  @GetMapping(path=UsersRestUriConstants.INDEX)
  public @ResponseBody Iterable<User> index() {
    return userRepository.findAll();
  }

  @GetMapping(path=UsersRestUriConstants.READ)
  public @ResponseBody User read(@RequestParam("id") Long id) {
    return userRepository.findOne(id);
  }

  @PostMapping(path=UsersRestUriConstants.CREATE)
  public @ResponseBody ResponseEntity<User> create(@RequestBody String requestString)
     throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    String email = Json.parse(requestString).asObject().get("email").asString();
    User user = new User();
    user.setEmail(email);
    userRepository.save(user);
    return new ResponseEntity<User>(user, HttpStatus.CREATED);
  }

  @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
  public @ResponseBody ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex){
    String body = generateErrorMessage("Duplicate entry on attribute " + ex.getConstraintName());
    return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({NullAttributeException.class, BlankAttributeException.class, InvalidAttributeException.class})
  public @ResponseBody ResponseEntity<String> handleNullAttributeException(AttributeException ex) {
    String body = generateErrorMessage(ex.getMessage());
    return new ResponseEntity<String>(body, HttpStatus.BAD_REQUEST);
  }

  private String generateErrorMessage(String message) {
    return "{\"error\":\"" + message + "\"}";
  }
}
