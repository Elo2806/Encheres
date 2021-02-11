/**
 * 
 */
package org.eni.encheres.dal;

import org.eni.encheres.dal.articles.ArticleDAO;
import org.eni.encheres.dal.articles.ArticleDAOImpl;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAO;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAOimpl;

/**
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public abstract class DAOFactory {

	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'ArticleDAO
	 * @return ArticleDAO
	 */
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'UtilisateurDAO
	 * @return UtilisateurDAO
	 */
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOimpl();
	}
	
}
