package api.session;

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
import api.session.Session;
import api.session.SessionsRestUriConstants;
import api.common.exceptions.AuthorizationException;

@Controller
public class SessionsController {

	@PostMapping(path=SessionsRestUriConstants.CREATE)
	public @ResponseBody ResponseEntity<Session> create(@RequestBody String requestString)
	 	throws AuthorizationException {
		String email = Json.parse(requestString).asObject().get("email").asString();
		String password = Json.parse(requestString).asObject().get("password").asString();
		AuthorizationService authService = new AuthorizationService(email, password);
		return new ResponseEntity<Session>(authService.authenticate(), HttpStatus.CREATED);
	}

	@ExceptionHandler(AuthorizationException.class)
	public @ResponseBody ResponseEntity<String> handleNullAttributeException(AuthorizationException ex) {
		String body = generateErrorMessage("Unauthorized");
		return new ResponseEntity<String>(body, HttpStatus.UNAUTHORIZED);
	}

	private String generateErrorMessage(String message) {
		return "{\"error\":\"" + message + "\"}";
	}
}
