package api;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty
	private Integer id;
	
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
