package api.session;

import api.user.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import java.util.UUID;
import java.util.Date;

@Entity
@Table(name = "sessions")
public class Session {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private Integer id;

	@Column(unique = true)
	@NotNull @NotBlank
	@JsonProperty
	private String token;

	@NotNull @NotBlank
	@JsonProperty
	private Date expiresAt;

	@OneToOne
	@JsonIgnore
	private User user;

	@Transient
	Date date;

	public String getToken(){
		return token;
	}

	public void generateToken(){
		this.token = UUID.randomUUID().toString();
		this.date = new Date();
		this.expiresAt = new Date(date.getTime() + 300000); // 5 minutes from token generation
	}

	public void setUser(User user) {
		this.user = user;
	}
}
