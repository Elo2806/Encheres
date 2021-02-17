/**
 * 
 */
package org.eni.encheres.dal.articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public interface ArticleDAO {

	/**
	 * 
	 * Méthode permettant de créer un nouvel articleVendu en base de données, de générer son identifiant et le mettre à jour.
	 * 
	 * @param newArticle ArticleVendu à ajouter en base 
	 * @return newArticle avec noArticle mis à jour
	 * @throws DALException si un problème survient lors de l'insertion en base
	 */
	ArticleVendu create(ArticleVendu newArticle) throws DALException;

	/**
	 * 
	 * Méthode permettant de récupérer la map des articles en fonction de leur id
	 * 
	 * @return map des articles en fonction de leur id
	 * @throws DALException si un problème survient lors de l'insertion en base
	 */
	Map<Integer, ArticleVendu> findAll() throws DALException;

	/**
	 * 
	 * Méthode permettant de mettre a jour 
	 * 
	 * @param articles
	 * @return
	 * @throws DALException 
	 */
	Map<Integer, ArticleVendu> updateEnchereMax(Map<Integer, ArticleVendu> articles) throws DALException;

	/**
	 * 
	 * Méthode permettant de récupérer un article en fonction de son id
	 * @param idArticle
	 * @return l'article recherché
	 * @throws DALException
	 */
	ArticleVendu selectArticleById(int idArticle) throws DALException;
	
}

