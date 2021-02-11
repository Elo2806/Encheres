/**
 * 
 */
package org.eni.encheres.dal;

import org.eni.encheres.dal.jdbc.ArticleDAOImpl;

/**
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public abstract class DAOFactory {

	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
	}
	
}
