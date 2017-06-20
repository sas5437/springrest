package api.common.exceptions;

import api.common.exceptions.AttributeException;

public class InvalidAttributeException extends AttributeException{

	private String value;

  public InvalidAttributeException(String anAttribute, String aValue){
		super(anAttribute);
		this.value = aValue;
  }

	public InvalidAttributeException(Throwable cause){
		super(cause);
		this.value = "";
	}

  public String getValue(){
    return value;
  }

  public String getMessage(){
    return value + " is not a valid " + attribute;
  }
}
