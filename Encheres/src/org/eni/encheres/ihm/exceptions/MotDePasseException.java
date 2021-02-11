package org.eni.encheres.ihm.exceptions;

public class MotDePasseException extends IHMException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MotDePasseException() {
	}

	public MotDePasseException(String message) {
		super(message);

	}

	public MotDePasseException(Throwable cause) {
		super(cause);
	}

	public MotDePasseException(String message, Throwable cause) {
		super(message, cause);
	}

}
