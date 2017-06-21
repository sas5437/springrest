package api.session;

import api.session.Session;
import api.session.SessionRepository;
import api.common.exceptions.NullAttributeException;
import api.common.exceptions.BlankAttributeException;
import api.common.exceptions.InvalidAttributeException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;
import java.util.regex.Pattern;

public class SessionTest {

  @Autowired
  Session session;

  private static final String UUID_PATTERN = "^[a-zA-Z0-9 -]{30,40}$";

  @Test
  public void init() {
    session = new Session();
    assertNull(session.getToken());
  }

  @Test
  public void generateTokenOk() {
    session = new Session();
    session.generateToken();
    assertNotNull(session.getToken());
    assertTrue(Pattern.matches(UUID_PATTERN, session.getToken()));
  }
}
