/**
 * 
 */
package org.eni.encheres.dal.articles;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.dal.exceptions.ConnectionException;

/**
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public interface ArticleDAO {

	/**
	 * 
	 * Méthode permettant de 
	 * @param newArticle
	 * @throws ConnectionException 
	 */
	void insert(ArticleVendu newArticle) throws ConnectionException;

	
	
}
