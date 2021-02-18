package org.eni.encheres.dal.exceptions;

/**
 * 
 * Classe d'exception générée lorsque l'utilisateur recherché existe dans
 * le systeme de persistance
 * 
 * @author Taharqa
 * 
 *         Créé le: 18 févr. 2021
 * 
 */
public class UtilisateurExistantException extends DALException {

	private static final long serialVersionUID = 1L;

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
