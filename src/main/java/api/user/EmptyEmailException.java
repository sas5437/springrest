package api.user;
public class EmptyEmailException extends Exception {

  public String getMessage() {
    return "Email cannot be empty";
  }
}
