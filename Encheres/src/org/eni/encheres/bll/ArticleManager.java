/**
 * 
 */
package org.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.Map;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.articles.ArticleDAO;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * @author Elodie Créé le: 10 févr. 2021 Modifié le: 10 févr. 2021
 */
public class ArticleManager {

	private static final String ERREUR_DAL = "Erreur lors du traitement en DAL";

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
	 * @param ville
	 * @param codePostal
	 * @param rue
	 * @throws BLLException
	 */
	public ArticleVendu ajouterArticle(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie, int prixInitial, String rue,
			String codePostal, String ville) throws BLLException {

		Retrait nouveauRetrait = new Retrait(rue, codePostal, ville, null);
		ArticleVendu nouvelArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
				vendeur, categorie, nouveauRetrait, prixInitial);

		try {
			nouvelArticle = this.articleDao.create(nouvelArticle);
		} catch (DALException dale) {
			throw new BLLException(ERREUR_DAL, dale);
		}
		return nouvelArticle;
	}

	/**
	 * 
	 * Méthode permettant de récupérer la map des articles en fonction de leur
	 * numero d'article
	 * 
	 * @return
	 * @throws BLLException
	 */
	public Map<Integer, ArticleVendu> getMapArticles() throws BLLException {
		Map<Integer, ArticleVendu> articles;

		try {
			articles = articleDao.findAll();
		} catch (DALException dale) {
			throw new BLLException(ERREUR_DAL, dale);
		}

		return articles;
	}

	/**
	 * 
	 * Méthode permettant de récupere un article en fonction de son numero d'article
	 * @param idArticle
	 * @return
	 * @throws BLLException
	 */
	public ArticleVendu recupererArticle(int idArticle) throws BLLException {
		ArticleVendu articleRecupere;
		try {
			articleRecupere = articleDao.selectArticleById(idArticle);
		} catch (DALException dale) {
			throw new BLLException(ERREUR_DAL, dale);
		}

		return articleRecupere;
	}
}
