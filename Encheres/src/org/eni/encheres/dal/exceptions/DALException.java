package org.eni.encheres.dal.exceptions;

/**
 * 
 * Classe d'exception générique générée lorsque qu'un problème au niveau de la
 * couche de persitance
 * 
 * @author Taharqa Créé le: 09 févr. 2021
 */
public class DALException extends Exception {

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
