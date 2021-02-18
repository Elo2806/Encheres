/**
 * 
 */
package org.eni.encheres.bll;


import java.time.LocalDateTime;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.encheres.EnchereDAO;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * @author Catherine Créé le: 11 févr. 2021 Modifié le: 11 févr. 2021
 */
public class EnchereManager {
	private static EnchereManager instance;
	private EnchereDAO enchereDao;

	/**
	 * Construteur
	 */
	private EnchereManager() {
		enchereDao = DAOFactory.getEnchereDAO();
	}

	public static EnchereManager getInstance() {
		if (instance == null) {
			instance = new EnchereManager();
		}

		return instance;
	}

	public Enchere creerEnchere(LocalDateTime dateEnchere, Integer montantEnchere, Utilisateur utilisateur, ArticleVendu article) throws BLLException {
		Enchere nouvelleEnchere = new Enchere(dateEnchere, montantEnchere, utilisateur, article);
		try {
			enchereDao.create(nouvelleEnchere);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'accès à la DAL", dale);
		}
		return nouvelleEnchere;
	}

	public Enchere modifierEnchere(LocalDateTime now, Integer propositionEnchere, Utilisateur encherisseur,
			ArticleVendu articleEnVente) throws BLLException {
		Enchere enchereAModifier = new Enchere(now, propositionEnchere, encherisseur, articleEnVente);
		try {
			enchereDao.update(enchereAModifier);
		} catch (DALException dale) {
			throw new BLLException("Erreur lors de l'accès à la DAL", dale);
		}
		return enchereAModifier;
		
	}
	/**
	 * 
	 * Méthode permettant de vérifier l'etat de l'enchere d'un article et modifier l'article en fonction du resultat
	 * @param articleAVerifier
	 * @return article article mis-à-jour
	 * @throws BLLException si un probleme survient lors de la modification de l'article dans ArticleManager
	 */
	public ArticleVendu DeterminerVainqueurEnchere(ArticleVendu articleAVerifier) throws BLLException {
		ArticleManager manager;
		ArticleVendu updatedArticle = articleAVerifier;
		
		if(LocalDateTime.now().isAfter(articleAVerifier.getDateFinEncheres())) {
			manager = ArticleManager.getInstance();
			if(!updatedArticle.getEnchereMax().getMontantEnchere().equals(0)) {
				updatedArticle.setPrixVente(updatedArticle.getEnchereMax().getMontantEnchere());
				updatedArticle.setAcheteur(updatedArticle.getEnchereMax().getUtilisateur());
				manager.modifierArticle(updatedArticle);
			}
			
		}
		
		return updatedArticle;
	}

	public ArticleVendu retirerArticle(ArticleVendu articleARetire) {
		ArticleManager manager;
		
		
		
		ArticleVendu updatedArticle = articleARetire;
		return updatedArticle;
	}
	
}