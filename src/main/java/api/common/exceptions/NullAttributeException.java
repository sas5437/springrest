package api.common.exceptions;

import api.common.exceptions.AttributeException;

public class NullAttributeException extends AttributeException {

	public NullAttributeException(String anAttribute){
		super(anAttribute);
	}

	public NullAttributeException(String anAttribute, Throwable cause){
		super(anAttribute, cause);
	}

  public String getMessage(){
    return attribute + " cannot be null";
  }
}
