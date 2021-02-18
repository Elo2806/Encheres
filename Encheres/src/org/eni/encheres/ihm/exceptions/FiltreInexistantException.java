package org.eni.encheres.ihm.exceptions;

public class FiltreInexistantException extends IHMException {

	private static final long serialVersionUID = 1L;

	public FiltreInexistantException() {
	}

	public FiltreInexistantException(String message) {
		super(message);
	}

	public FiltreInexistantException(Throwable cause) {
		super(cause);
	}

	public FiltreInexistantException(String message, Throwable cause) {
		super(message, cause);
	}

}
