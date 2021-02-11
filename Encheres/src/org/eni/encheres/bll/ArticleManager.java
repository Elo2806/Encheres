/**
 * 
 */
package org.eni.encheres.bll;

import java.time.LocalDate;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.articles.ArticleDAO;

/**
 * @author Elodie
 * Créé le: 10 févr. 2021
 * Modifié le: 10 févr. 2021
 */
public class ArticleManager {

	private ArticleDAO articleDao;

	/**
	 * Constructeur
	 */
	public ArticleManager() {
		this.articleDao = DAOFactory.getArticleDAO();
	}
	
	public ArticleVendu ajouterArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Utilisateur vendeur, Categorie categorie) {
		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur, categorie);
		
		this.articleDao.insert(newArticle);
				
		return newArticle;
	}
	
}
