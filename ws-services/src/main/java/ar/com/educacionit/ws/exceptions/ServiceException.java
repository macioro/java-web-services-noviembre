package ar.com.educacionit.ws.exceptions;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8829684684823809217L;

	public ServiceException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ServiceException(String arg0) {
		super(arg0);
	}

	//alt+shift+s
	
}