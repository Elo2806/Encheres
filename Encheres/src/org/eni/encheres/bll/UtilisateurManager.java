package org.eni.encheres.bll;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAO;

/**
 * 
 * @author ElCaTar
 * Créé le: 18 févr. 2021
 * Modifié le: 18 févr. 2021
 */
public class UtilisateurManager {

	private static UtilisateurManager instance;
	private UtilisateurDAO utilisateurdao;

	/**
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

	/**
	 * Méthode permettant de creer un utilisateur dans le système de persistance
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
	 * @throws BLLException si probleme lors du passage en DAL
	 */
	public void ajouterUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse) throws BLLException {
		Utilisateur nouvelUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
				motDePasse);

		try {
			utilisateurdao.create(nouvelUtilisateur);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL", dale);
		}
		
		offrirCreditAuxCentPremiers(nouvelUtilisateur);

	}
	
	
	/**
	 * Méthode permettant d'offrir 100 points de crédit aux 100 premiers utilisateurs crées
	 * 
	 * @param nouvelUtilisateur
	 * @throws BLLException si probleme lors du passage en DAL
	 */
	private void offrirCreditAuxCentPremiers(Utilisateur nouvelUtilisateur) throws BLLException {
		if (nouvelUtilisateur.getNoUtilisateur()<=100) {
			nouvelUtilisateur.setCredit(100);
			try {
				utilisateurdao.update(nouvelUtilisateur);
			} catch (DALException dale) {
				throw new BLLException("Erreur lors de l'ajout du crédit", dale);
			}
		}
	}

	/**
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
	 * @return l'utilisateur modifié
	 * @throws BLLException si probleme lors du passage en DAL
	 */
	public Utilisateur modifierUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, Integer noUtilisateur) throws BLLException {
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, noUtilisateur);
		try {
			utilisateurdao.update(user);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL", dale);
		}
		return user;
	}

	/**
	 * Méthode permettant de supprimer un compte dans le système de persistance
	 * 
	 * @param utilisateurAffiche
	 * @return l'utilisateur créé
	 * @throws BLLException si probleme lors du passage en DAL
	 */
	public Utilisateur supprimerCompte(Utilisateur utilisateurAffiche) throws BLLException {
		try {
			utilisateurdao.updateSupprime(utilisateurAffiche);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL", dale);
		}
		return utilisateurAffiche;
		
	}

	/**
	 * Méthode permettant d'appeler un contrôle du pseudo et de l'email dans le
	 * systeme de persistance (pour créer un compte)
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
	
	/**
	 * Méthode permettant de contrôler l'existance d'un email dans la système de persistance
	 * 
	 * @param email
	 * @throws BLLException si l'email n'existe pas
	 */
	public void controleEmailExistant(String email) throws BLLException {
		try {
			utilisateurdao.controleEmailExistence(email);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL", dale);
		}
	}
	
	/**
	 * Méthode permettant de  contrôler l'existance d'un pseudo dans la système de persistance
	 * @param pseudo
	 * @throws BLLException si le pseudo n'existe pas
	 */
	public void controlePseudoExistant(String pseudo) throws BLLException {
		try {
			utilisateurdao.controlePseudoExistence(pseudo);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL", dale);
		}
	}

	/**
	 * Méthode permettant de vérifier et récuperer un utilisateur existant selon son
	 * pseudo et son mot de passe (pour se connecter)
	 * 
	 * @param identifiant
	 * @param pseudo
	 * @return l'utilisateur recherché
	 * @throws BLLException si l'utilisateur n'est pas dans le système de persistance
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
