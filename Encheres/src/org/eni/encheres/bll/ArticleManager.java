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

	private static ArticleManager instance;
	private ArticleDAO articleDao;

	/**
	 * Constructeur
	 */
	public ArticleManager() {
		this.articleDao = DAOFactory.getArticleDAO();
	}
	
	public static ArticleManager getInstance() {
		if(instance==null) {
			instance = new ArticleManager();
		}
		return instance;
	}
	public void ajouterArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Utilisateur vendeur, Categorie categorie) {
		
		ArticleVendu nouvelArticle = creerArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres,
				vendeur, categorie);
		this.articleDao.insert(nouvelArticle);
	}
	
	public ArticleVendu creerArticle(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, Utilisateur vendeur, Categorie categorie) {
		
		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur, categorie);
		
		return newArticle;
	}
	
}
