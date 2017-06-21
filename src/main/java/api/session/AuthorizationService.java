package api.session;

import api.session.Session;
import api.session.SessionRepository;
import api.user.User;
import api.user.UserRepository;
import api.common.exceptions.AuthorizationException;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthorizationService {

	private static final Pattern pattern = Pattern.compile(User.PASSWORD_REGEX);

	private Matcher matcher;
	private User user;
	private Session session;
	@Autowired private SessionRepository sessionRepository;
	@Autowired private UserRepository userRepository;
	private String email;
	private String password;

	public AuthorizationService(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Session authenticate() throws AuthorizationException {
		validatePassword(password);
		try {
			user = userRepository.findByEmail(email);
			return createSession(user);
		} catch (Exception ex) { 
			throw new AuthorizationException();
		}
	}

	private Session createSession(User user) {
		session = new Session();
		session.setUser(user);
		session.generateToken();
		sessionRepository.save(session);
		return session;
	}

	private void validatePassword(String password) {
		matcher = pattern.matcher(password);
		if (!matcher.find()) {
			throw new AuthorizationException();
		}
	}
}
