package ar.com.educacionit.ws.exceptions;

public class GenericExeption extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8695764074997465305L;

	public GenericExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericExeption(String message) {
		super(message);
	}

	
}
