package org.eni.encheres.dal.exceptions;

public class DALException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DALException() {
		super();
	}

	public DALException(String message, Throwable cause) {
		super(message, cause);
	}

	public DALException(String message) {
		super(message);
	}

	public DALException(Throwable cause) {
		super(cause);
	}

}
