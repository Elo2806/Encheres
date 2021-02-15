package org.eni.encheres.dal.utilisateurs;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * 
 * Cette classe permet de centraliser les méthodes d'intéraction avec le système de persistance
 * 
 * @author Taharqa
 * Créé le: 11 févr. 2021
 * Mofifié le: 11 févr. 2021
 */
public interface UtilisateurDAO {
	
	/**
	 * 
	 * Méthode permettant de créer un nouvel utilisateur en base de données, de générer son identifiant et le mettre à jour.
	 * @param nouvelUtilisateur Utilisateur à ajouter en base 
	 * @return nouvelUtilisateur avec noUtilisateur mis à jour
	 * @throws DALException si un problème survient lors de l'insertion en base
	 */
	public Utilisateur create(Utilisateur nouvelUtilisateur) throws DALException;

	/**
	 * 
	 * Méthode permettant de contrôler la non existence d'un pseudo ou d'un email dans le systeme de persistance
	 * @param pseudo pseudo à tester
	 * @param email email à tester
	 * @throws DALException si le pseudo ou l'email existe déjà
	 */
	public void controleUtilisateurExistence(String pseudo, String email) throws DALException;

	
	/**
	 * 
	 * Méthode permettant de contrôler l'existence de l'utilisateur dans le systeme de persistance
	 * @param identifiant à tester
	 * @param motDePasse à tester
	 * @return le n° utilisateur qui est égale à 0 si non trouvé
	 * @throws DALException
	 */
	public Utilisateur controleIdentifiantsExistants(String identifiant, String motDePasse) throws DALException;
	
	/**
	 * 
	 * Méthode permettant de controler l'existence d'un email dans le système de persistance
	 * @param email
	 * @throws DALException 
	 */
	public void controleEmailExistence(String email) throws DALException;
	
	/**
	 * 
	 * Méthode permettant de controler l'existence d'un pseudo dans le système de persistance
	 * @param email
	 * @throws DALException 
	 */
	public void controlePseudoExistence(String pseudo) throws DALException;
	
	/**
	 * 
	 * Méthode permettant de modifier un utilisateur en base de données
	 * @param utilisateurAModifier
	 * @return
	 * @throws DALException
	 */
	public Utilisateur update(Utilisateur utilisateurAModifier) throws DALException;

	

	
	
}
