/**
 * 
 */
package org.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.Map;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.articles.ArticleDAO;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * @author Elodie Créé le: 10 févr. 2021 Modifié le: 10 févr. 2021
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
		if (instance == null) {
			instance = new ArticleManager();
		}
		return instance;
	}

	/**
	 * 
	 * Méthode permettant d'ajouter un article dans le systeme de persistance
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 * @throws BLLException
	 */
	public void ajouterArticle(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie) throws BLLException {

		ArticleVendu nouvelArticle = creerArticle(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur,
				categorie);
		try {
			this.articleDao.create(nouvelArticle);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'acces à la DAL", dale);
		}
	}

	/**
	 * 
	 * Méthode permettant de créer un objet Article à partir des paramètres
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 * @return
	 */
	public ArticleVendu creerArticle(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie) {

		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur,
				categorie);

		return newArticle;
	}

	public Map<Integer, ArticleVendu> getMapArticles() throws BLLException {
		 Map<Integer, ArticleVendu> articles;
		 
		 try {
			articles = articleDao.findAll();
		} catch (DALException dale) {
			throw new BLLException("Erreur lors du traitement en DAL",dale);
		}
		 
		return articles;
	}

}
