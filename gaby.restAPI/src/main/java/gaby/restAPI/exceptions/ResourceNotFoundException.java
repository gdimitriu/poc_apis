package gaby.restAPI.exceptions;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = -983521777357764929L;
	public ResourceNotFoundException(String name) {
		super(name+ " : not found");
	}

}
