package org.eni.encheres.bll;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
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
		if(instance==null) {
			instance =  new UtilisateurManager();
		}
		
		return instance;
	}
	
	public void ajouterUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse) throws BLLException {
		Utilisateur nouvelUtilisateur = creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
		
		try {
			utilisateurdao.create(nouvelUtilisateur);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL",dale);
		}
	
	}
		
	private Utilisateur creerUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse) {
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 0, false);
		return user;
	}

	/**
	 * 
	 * Méthode permettant d'appeler un contrôle du pseudo et de l'email dans le systeme de persistance
	 * @param pseudo
	 * @param email
	 * @throws BLLException si un des deux identifiants existent en base
	 */
	public void controleIdentifiantNewUtilisateur(String pseudo,String email) throws BLLException {
		try {
			utilisateurdao.controleUtilisateurExistence(pseudo,email);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL",dale);
		}
	}

	public int verifierIdMdP(String identifiant, String motDePasse) throws BLLException {
		int noUtilisateur;
		try {
			noUtilisateur = utilisateurdao.controleIdentifiantsExistants (identifiant, motDePasse);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors des opérations en DAL",dale);
		}
		
		return 0;
	}
		
	
	
	
}
