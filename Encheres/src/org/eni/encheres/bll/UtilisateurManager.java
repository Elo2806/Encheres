package org.eni.encheres.bll;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAO;

public class UtilisateurManager {

	private static UtilisateurManager instance;
	private UtilisateurDAO utilisateurdao;
	
	
	/**
	 * 
	 * Constructeur
	 */
	private UtilisateurManager() {
		
	}
	
	public static UtilisateurManager getInstance() {
		if(instance==null) {
			instance =  new UtilisateurManager();
		}
		
		return instance;
	}
	
	
	
	public Utilisateur creerUtilisateur(String pseudo,String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String motDePasse) {
		Utilisateur user = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, 0, false);
		
		
	
		return null;
	}

	
	
}
