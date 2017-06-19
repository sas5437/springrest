package api.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty
	private Integer id;

	@Column(unique = true)
	@JsonProperty
	private String email;

  public Integer getId(){
		return id;
	}

	public String geEmail(){
		return email;
	}

	public boolean setEmail(String email){
		this.email = email;
		return true;
	}
}
