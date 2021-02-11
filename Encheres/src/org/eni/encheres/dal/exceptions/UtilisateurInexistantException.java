/**
 * 
 */
package org.eni.encheres.dal.exceptions;

/**
 * Classe représentant l'erreur d'inexistance d'un utilisateur en systeme de persistance
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public class UtilisateurInexistantException extends DALException {

	/**
	 * Constructeur
	 */
	public UtilisateurInexistantException() {
	}

	/**
	 * Constructeur
	 * @param message
	 * @param cause
	 */
	public UtilisateurInexistantException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructeur
	 * @param message
	 */
	public UtilisateurInexistantException(String message) {
		super(message);
	}

	/**
	 * Constructeur
	 * @param cause
	 */
	public UtilisateurInexistantException(Throwable cause) {
		super(cause);
	}

}
