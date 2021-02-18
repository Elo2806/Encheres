/**
 * 
 */
package org.eni.encheres.dal.articles;

import java.util.Map;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * 
 * Interface regroupant les méthodes permettant d'interagir avec le systeme de persistance sur les objets métiers liés aux Articles.
 * 
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public interface ArticleDAO {

	/**
	 * 
	 * Méthode permettant d'insérer un articleVendu au systeme de persistance, de générer son identifiant et de le mettre à jour.
	 * 
	 * @param newArticle ArticleVendu à ajouter au systeme de persistance
	 * @return ArticleVendu avec noArticle mis à jour
	 * @throws DALException si un problème survient lors de l'insertion en base
	 */
	ArticleVendu create(ArticleVendu newArticle) throws DALException;

	/**
	 * 
	 * Méthode permettant de récupérer dans le systeme de persistence la map de l'ensemble des articles en fonction de leur id.
	 * 
	 * @return map des articles en fonction de leur id
	 * @throws DALException si un problème survient lors de l'insertion dans le systeme
	 */
	Map<Integer, ArticleVendu> findAll() throws DALException;

	/**
	 * 
	 * Méthode permettant de mettre à jour l'enchere maximale de chaque article de la map d'articles en parametre.
	 * 
	 * @param articles map des articles à mettre à jour
	 * @return map des articles mis à jour
	 * @throws DALException si un problème survient lors de la recherche dans le systeme
	 */
	Map<Integer, ArticleVendu> updateEnchereMax(Map<Integer, ArticleVendu> articles) throws DALException;

	/**
	 * 
	 * Méthode permettant de modifier un article dans le systeme de persistance
	 * 
	 * @param updatedArticle article à modifier
	 * @throws DALException si un problème survient lors de la mise à jour dans le systeme
	 */
	void update(ArticleVendu updatedArticle) throws DALException;
	

	
}

