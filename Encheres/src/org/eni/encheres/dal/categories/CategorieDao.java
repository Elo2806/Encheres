package org.eni.encheres.dal.categories;

import java.util.Map;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.exceptions.DALException;

public interface CategorieDao {

	Map<Integer,Categorie> findAll() throws DALException;
	
	Categorie find(int categorieId) throws DALException;
}
