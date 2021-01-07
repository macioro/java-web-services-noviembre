package ar.com.educacionit.ws.exceptions;

public class DuplicatedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4459547583129424007L;

	public DuplicatedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DuplicatedException(String message) {
		super(message);
	}

	
}
