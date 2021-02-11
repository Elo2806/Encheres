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

	public int controleIdentifiantsExistants(String identifiant, String motDePasse) throws DALException;
	
}
