package org.eni.encheres.dal.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

public class CategorieDAOimpl implements CategorieDao {

	private static final String COL_LIBELLE = "libelle";
	private static final String COL_NO_CATEGORIE = "no_categorie";
	
	private static final String SQL_SELECT_CATEGORIES = "SELECT no_categorie,libelle FROM categories";

	@Override
	public List<Categorie> findAll() throws DALException {

		List<Categorie> listeCategories = new ArrayList<>();

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Traitement de la requete SQL
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_CATEGORIES);

				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int numCategorie = rs.getInt(COL_NO_CATEGORIE);
					String lib = rs.getString(COL_LIBELLE);

					Categorie categorie = new Categorie(lib);
					categorie.setNoCategorie(numCategorie);

					listeCategories.add(categorie);
				}
				
				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors de la selection en base", sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

		return listeCategories;
	}

}
