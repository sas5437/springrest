package api.user;
public class InvalidEmailException extends Exception {
  private String email;

  public InvalidEmailException(String email){
    this.email = email;
  }

  public String getEmail(){
    return email;
  }

  public String getMessage(){
    return email + " is not a valid email address";
  }
}
