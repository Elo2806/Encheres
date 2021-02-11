package org.eni.encheres.dal.categories;

import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.exceptions.DALException;

public interface CategorieDao {

	List<Categorie> findAll() throws DALException;

}
