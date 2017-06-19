package api.user;

import api.user.NullEmailException;
import api.user.EmptyEmailException;
import api.user.InvalidEmailException;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

	private static final Pattern VALID_EMAIL_REGEX = Pattern.compile(
		"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty
	private Integer id;

	@Column(unique = true)
	@Email @NotNull @NotBlank
	@JsonProperty
	private String email;

  public Integer getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email)
		throws NullEmailException, EmptyEmailException, InvalidEmailException {
		if(null == email)
			throw new NullEmailException();
		else if("" == email.trim())
			throw new EmptyEmailException();
		else if(!isValidEmail(email))
			throw new InvalidEmailException(email);
		this.email = email;
	}

	private boolean isValidEmail(String email) {
		Matcher matcher = VALID_EMAIL_REGEX.matcher(email);
    return matcher.find();
	}
}
