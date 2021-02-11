package org.eni.encheres.dal.exceptions;

public class RequeteSQLException extends DALException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequeteSQLException() {
	}

	public RequeteSQLException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequeteSQLException(String message) {
		super(message);
	}

	public RequeteSQLException(Throwable cause) {
		super(cause);
	}

}
