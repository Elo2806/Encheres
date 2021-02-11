package org.eni.encheres.dal.exceptions;

public class UtilisateurExistantException extends DALException {

	public UtilisateurExistantException() {
	}

	public UtilisateurExistantException(String message, Throwable cause) {
		super(message, cause);
	}

	public UtilisateurExistantException(String message) {
		super(message);
	}

	public UtilisateurExistantException(Throwable cause) {
		super(cause);
	}

}
