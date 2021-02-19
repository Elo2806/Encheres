package org.eni.encheres.bll;

import java.util.Map;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.categories.CategorieDao;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * 
 * @author ElCaTar
 * Créé le: 18 févr. 2021
 * Modifié le: 18 févr. 2021
 */
public class CategorieManager {
	
	private static final String ERREUR_DAL = "Erreur lors du traitement en DAL";
	private static CategorieManager single;
	private static CategorieDao categorieDao;
	
	/**
	 * Constructeur
	 */
	private CategorieManager() {
		categorieDao = DAOFactory.getCatogorieDAO(); 
	}
	
	
	public static CategorieManager getInstance() {
		if(single == null) {
			single = new CategorieManager();
		}
		return single;
	}

	/**
	 * 
	 * Méthode permettant de récupérer les catégories dans le système de persistance
	 * 
	 * @return les catégories
	 * @throws BLLException si probleme lors du passage en DAL
	 */
	public Map<Integer,Categorie> getCategories() throws BLLException {
		Map<Integer,Categorie> categories = null;
		
		try {
			categories = categorieDao.findAll();
		} catch (DALException dale) {
			throw new BLLException(ERREUR_DAL,dale);
		}
		
		return categories;
	}
	
	
}
