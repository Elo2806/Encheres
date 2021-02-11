package org.eni.encheres.bll;

import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.categories.CategorieDao;
import org.eni.encheres.dal.exceptions.DALException;

public class CategorieManager {
	
	private static CategorieManager single;
	private static CategorieDao categorieDao;
	
	private CategorieManager() {
		
	}
	
	
	public static CategorieManager getInstance() {
		if(single == null) {
			single = new CategorieManager();
		}
		return single;
	}

	
	public List<Categorie> getCategories() {
		List<Categorie> categories = null;
		
		try {
			categories = categorieDao.findAll();
		} catch (DALException e) {
			e.printStackTrace();
		}
		
		return categories;
	}
	
	
}
