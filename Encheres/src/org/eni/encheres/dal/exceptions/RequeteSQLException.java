package org.eni.encheres.dal.exceptions;

/**
 * 
 * Classe d'exception générée lorsque qu'un problème survient lors de l'exécution d'une requête SQL
 * 
 * @author Taharqa
 * Créé le: 18 févr. 2021
 * Mofifié le: 18 févr. 2021
 */
public class RequeteSQLException extends DALException {

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
