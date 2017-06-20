package api.common.exceptions;

public class AttributeException extends RuntimeException {

	protected String attribute;

	public AttributeException(){
		this.attribute = "";
	}

	public AttributeException(String anAttribute){
		super(anAttribute + " raised an exception");
		this.attribute = anAttribute;
	}
	
	public AttributeException(Throwable cause){
		super(cause);
		this.attribute = "";
	}

	public AttributeException(String anAttribute, Throwable cause){
		super(anAttribute + " raised an exception", cause);
		this.attribute = anAttribute;
	}
	
	public String getAttribute(){
		return attribute;
	}
}
