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

	public void encherir(LocalDateTime dateEnchere, Integer montantEnchere, Utilisateur utilisateur, ArticleVendu article) throws BLLException {
		Enchere nouvelleEnchere = new Enchere(dateEnchere, montantEnchere, utilisateur, article);
		try {
			enchereDao.create(nouvelleEnchere);
		} catch (DALException e) {
			throw new BLLException("Erreur lors de l'accès à la DAL");
		}
	}
}