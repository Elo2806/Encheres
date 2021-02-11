package org.eni.encheres.dal;

import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.categories.CategorieDao;
import org.eni.encheres.dal.exceptions.DALException;

public class CategorieDAOimpl implements CategorieDao {

	@Override
	public List<Categorie> findAll() throws DALException {
		return null;
	}

}
