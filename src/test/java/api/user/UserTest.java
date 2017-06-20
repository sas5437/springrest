package api.user;

import api.user.User;
import api.user.UserRepository;
import api.common.exceptions.NullAttributeException;
import api.common.exceptions.BlankAttributeException;
import api.common.exceptions.InvalidAttributeException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.Assert.*;

public class UserTest {

  @Autowired
  User user;

  @Test
  public void init(){
    user = new User();
    assertNotNull(user);
    assertNull(user.getId());
    assertNull(user.getEmail());
  }

  @Test
  public void emailCanBeValid()
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    user = new User();
    String email = "test+email@gmail.com";
    assertNull(user.getEmail());
    user.setEmail(email);
    assertEquals(user.getEmail(), email);
  }

  @Test(expected=NullAttributeException.class)
  public void emailCannotBeNull()
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    user = new User();
    user.setEmail(null);
  }

  @Test(expected=BlankAttributeException.class)
  public void emailCannotBeEmpty()
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    user = new User();
    assertNull(user.getEmail());
    user.setEmail("");
    assertEquals(user.getEmail(), "");
  }

  @Test(expected=InvalidAttributeException.class)
  public void emailCannotBeInvalidCaseOne()
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    user = new User();
    assertNull(user.getEmail());
    user.setEmail("invalid@email.com@gmail.com");
  }

  @Test(expected=InvalidAttributeException.class)
  public void emailCannotBeInvalidCaseTwo()
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    user = new User();
    assertNull(user.getEmail());
    user.setEmail("invalidemailatgmaildotcom");
  }
}
