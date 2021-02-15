package org.eni.encheres.bll;

import java.util.List;
import java.util.Map;

import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.categories.CategorieDao;
import org.eni.encheres.dal.exceptions.DALException;

public class CategorieManager {
	
	private static CategorieManager single;
	private static CategorieDao categorieDao;
	
	private CategorieManager() {
		categorieDao = DAOFactory.getCatogorieDAO(); 
	}
	
	
	public static CategorieManager getInstance() {
		if(single == null) {
			single = new CategorieManager();
		}
		return single;
	}

	
	public Map<Integer,Categorie> getCategories() throws BLLException {
		Map<Integer,Categorie> categories = null;
		
		try {
			categories = categorieDao.findAll();
		} catch (DALException dale) {
			throw new BLLException("Erreur lors du traitement en DAL",dale);
		}
		
		return categories;
	}
	
	
}
