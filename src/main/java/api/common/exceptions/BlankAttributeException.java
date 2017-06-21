package api.common.exceptions;

import api.common.exceptions.AttributeException;

public class BlankAttributeException extends AttributeException {

  public BlankAttributeException(String anAttribute){
    super(anAttribute);
  }

  public BlankAttributeException(String anAttribute, Throwable cause){
    super(anAttribute, cause);
  }

  public String getMessage() {
    return attribute + " cannot be blank";
  }
}
