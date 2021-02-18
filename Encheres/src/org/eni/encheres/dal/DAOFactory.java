/**
 * 
 */
package org.eni.encheres.dal;

import org.eni.encheres.dal.articles.ArticleDAO;
import org.eni.encheres.dal.articles.ArticleDAOImpl;
import org.eni.encheres.dal.categories.CategorieDAOimpl;
import org.eni.encheres.dal.categories.CategorieDao;
import org.eni.encheres.dal.encheres.EnchereDAO;
import org.eni.encheres.dal.encheres.EnchereDAOimpl;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAO;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAOimpl;

/**
 * Classe Utilitaire centralisant les methodes de création des objets DAO.
 *
 * @author Elodie
 * Créé le: 11 févr. 2021
 *
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
	
	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'EnchereDAO
	 * @return EnchereDAO
	 */
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOimpl();
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'EnchereDAO
	 * @return EnchereDAO
	 */
	public static CategorieDao getCatogorieDAO() {
		return new CategorieDAOimpl();
	}
	

}
