package org.eni.encheres.dal.exceptions;


/**
 * 
 * Classe d'exception générée lors d'un problème de connexion avec le systeme de persitance
 * 
 * @author Taharqa
 * Créé le: 11 févr. 2021
 *
 */
public class ConnectionException extends DALException {

	private static final long serialVersionUID = 1L;

	public ConnectionException() {
		super();
	}

	public ConnectionException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionException(String message) {
		super(message);
	}

	public ConnectionException(Throwable cause) {
		super(cause);
	}

	
	
}
