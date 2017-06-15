package api;

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

import api.User;
import api.UserRepository;

@Controller
@RequestMapping(path="/users")
public class UsersController {
	@Autowired
  
	private UserRepository userRepository;
	
	@PostMapping
	public @ResponseBody ResponseEntity<User> create(@RequestBody String requestString) {
		String email = Json.parse(requestString).asObject().get("email").asString();
		User user = new User();
		user.setEmail(email);
		userRepository.save(user);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@GetMapping
	public @ResponseBody Iterable<User> index() {
		return userRepository.findAll();
	}

	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public @ResponseBody ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex){
		String body = "{\"error\":\"Duplicate entry on attribute " + ex.getConstraintName() + "\"}";
		return new ResponseEntity<String>(body, HttpStatus.OK);
	}
}
