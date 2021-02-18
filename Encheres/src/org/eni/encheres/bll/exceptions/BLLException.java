package org.eni.encheres.bll.exceptions;

/**
 * @author AlCaTar
 * Créé le: 18 févr. 2021
 * Modifié le: 18 févr. 2021
 */
public class BLLException extends Exception {

	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 */
	public BLLException() {
	}

	/**
	 * Constructeur avec message
	 * @param message
	 */
	public BLLException(String message) {
		super(message);
	}

	/**
	 * Constructeur avec cause
	 * @param cause
	 */
	public BLLException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructeur complet
	 * @param message
	 * @param cause
	 */
	public BLLException(String message, Throwable cause) {
		super(message, cause);
	}

}
