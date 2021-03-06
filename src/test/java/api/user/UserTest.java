package api.user;

import api.user.User;
import api.user.UserRepository;
import api.user.NullEmailException;
import api.user.EmptyEmailException;

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
    throws NullEmailException, EmptyEmailException, InvalidEmailException {
    user = new User();
    String email = "test+email@gmail.com";
    assertNull(user.getEmail());
    user.setEmail(email);
    assertEquals(user.getEmail(), email);
  }

  @Test(expected=NullEmailException.class)
  public void emailCannotBeNull()
    throws NullEmailException, EmptyEmailException, InvalidEmailException {
    user = new User();
    user.setEmail(null);
  }

  @Test(expected=EmptyEmailException.class)
  public void emailCannotBeEmpty()
    throws NullEmailException, EmptyEmailException, InvalidEmailException {
    user = new User();
    assertNull(user.getEmail());
    user.setEmail("");
    assertEquals(user.getEmail(), "");
  }

  @Test(expected=InvalidEmailException.class)
  public void emailCannotBeInvalidCaseOne()
    throws NullEmailException, EmptyEmailException, InvalidEmailException {
    user = new User();
    assertNull(user.getEmail());
    user.setEmail("invalid@email.com@gmail.com");
  }

  @Test(expected=InvalidEmailException.class)
  public void emailCannotBeInvalidCaseTwo()
    throws NullEmailException, EmptyEmailException, InvalidEmailException {
    user = new User();
    assertNull(user.getEmail());
    user.setEmail("invalidemailatgmaildotcom");
  }
}
