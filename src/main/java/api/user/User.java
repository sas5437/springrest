package api.user;

import api.common.exceptions.NullAttributeException;
import api.common.exceptions.BlankAttributeException;
import api.common.exceptions.InvalidAttributeException;
import api.session.Session;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

  public static final String EMAIL_REGEX = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
  public static final String PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@*#-$]){8,20}$";

  private static final Pattern VALID_EMAIL_REGEX = Pattern.compile(
    EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

  private static final Pattern VALID_PASSWORD_REGEX = Pattern.compile(
    "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@*#-$]){8,20}$");

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @JsonProperty
  private Integer id;

  @Column(unique = true)
  @Email @NotNull @NotBlank
  @JsonProperty
  private String email;

  @Column(unique = true)
  @JsonIgnore
  private String password;

  @Transient
  private Matcher matcher;

  public Integer getId(){
    return id;
  }

  public String getEmail(){
    return email;
  }

  public void setEmail(String email)
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    if(null == email)
      throw new NullAttributeException("email address");
    else if(email.trim().equals(""))
      throw new BlankAttributeException("email address");
    else if(!isValidEmail(email))
      throw new InvalidAttributeException("email address", email);
    this.email = email;
  }

  public void setPassword(String aPassword)
    throws NullAttributeException, BlankAttributeException, InvalidAttributeException {
    if(null == aPassword)
      throw new NullAttributeException("password");
    else if(aPassword.trim().equals(""))
      throw new BlankAttributeException("password");
    else if(!isValidPassword(aPassword))
      throw new InvalidAttributeException("password", aPassword);
    this.password = aPassword;
  }

  public boolean doesPasswordMatch(String aString){
    if(aString.equals(password)){
      return true;
    }
    return false;
  }

  private boolean isValidEmail(String email) {
    matcher = VALID_EMAIL_REGEX.matcher(email);
    return matcher.find();
  }

  private boolean isValidPassword(String aPassword) {
    matcher = VALID_PASSWORD_REGEX.matcher(password);
    return matcher.find();
  }
}
