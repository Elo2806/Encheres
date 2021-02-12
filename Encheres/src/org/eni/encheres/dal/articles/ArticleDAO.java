/**
 * 
 */
package org.eni.encheres.dal.articles;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public interface ArticleDAO {

	/**
	 * 
	 * Méthode permettant de créer un nouvel articleVendu en base de données, de générer son identifiant et le mettre à jour.
	 * @param newArticle ArticleVendu à ajouter en base 
	 * @return newArticle avec noArticle mis à jour
	 * @throws DALException si un problème survient lors de l'insertion en base
	 */
	void create(ArticleVendu newArticle) throws DALException;

	
	
}
