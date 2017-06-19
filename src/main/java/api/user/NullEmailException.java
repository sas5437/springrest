package api.user;
public class NullEmailException extends Exception {

  public String getMessage(){
    return "Email cannot be null";
  }
}
