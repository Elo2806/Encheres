package org.eni.encheres.ihm.exceptions;

public class IHMException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IHMException() {

	}

	public IHMException(String message) {
		super(message);

	}

	public IHMException(Throwable cause) {
		super(cause);
	}

	public IHMException(String message, Throwable cause) {
		super(message, cause);
	}

}
