package org.eni.encheres.bll;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAO;

public class UtilisateurManager {

	private static UtilisateurManager instance;
	private UtilisateurDAO utilisateurdao;

	/**
	 * 
	 * Constructeur
	 */
	private UtilisateurManager() {
		utilisateurdao = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}

		return instance;
	}

	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		Utilisateur nouvelUtilisateur = creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse);

		try {
			utilisateurdao.create(nouvelUtilisateur);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL", dale);
		}

	}

	/**
	 * 
	 * Méthode permettant de modifier les données d'un compte utilisateur
	 * 
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @return
	 * @throws BLLException
	 */
	public Utilisateur modifierUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer noUtilisateur) throws BLLException {
		Utilisateur user = creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, noUtilisateur);
		try {
			utilisateurdao.update(user);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL", dale);
		}
		return user;
	}

	/**
	 * 
	 * Méthode permettant de créer une instance d'utilisateur avec noUtilisateur récupéré
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param noUtilisateur
	 * @return
	 */
	private Utilisateur creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer noUtilisateur) {
		Utilisateur user = creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		user.setNoUtilisateur(noUtilisateur);
		return user;
	}

	/**
	 * 
	 * Méthode permettant de créer une instance d'utilisateur  
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @return
	 */
	private Utilisateur creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) {
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 0,
				false);
		return user;
	}
	
	

	/**
	 * 
	 * Méthode permettant d'appeler un contrôle du pseudo et de l'email dans le
	 * systeme de persistance (pour créer un compte) // TODO enlever comm entre
	 * parenthese + changer nom méthode
	 * 
	 * @param pseudo
	 * @param email
	 * @throws BLLException
	 *             si un des deux identifiants existent en base
	 */
	public void controleIdentifiantNewUtilisateur(String pseudo, String email) throws BLLException {
		try {
			utilisateurdao.controleUtilisateurExistence(pseudo, email);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL", dale);
		}
	}
	
	public void controleEmailExistant(String email) throws BLLException {
		try {
			utilisateurdao.controleEmailExistence(email);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL", dale);
		}
	}
	
	public void controlePseudoExistant(String pseudo) throws BLLException {
		try {
			utilisateurdao.controlePseudoExistence(pseudo);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL", dale);
		}
	}

	/**
	 * 
	 * Méthode permettant de vérifier et récuperer un utilisateur existant selon son
	 * pseudo et son mot de passe (pour se connecter) // TODO enlever comm entre
	 * parenthese + changer nom méthode
	 * 
	 * @param identifiant
	 * @param pseudo
	 * @return
	 * @throws BLLException
	 */
	public Utilisateur rechercherUtilisateur(String identifiant, String motDePasse) throws BLLException {
		Utilisateur utilisateur = null;
		try {
			utilisateur = utilisateurdao.controleIdentifiantsExistants(identifiant, motDePasse);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL", dale);
		}

		return utilisateur;
	}

}
