package org.eni.encheres.dal.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * 
 *  Classe concrète regroupant les méthodes permettant d'interagir avec la base de données SQL sur les objets métiers liés aux Catégories.
 * 
 * @author Taharqa
 * Créé le: 9 févr. 2021
 * 
 */
public class CategorieDAOimpl implements CategorieDao {


	private static final String COL_CAT_LIBELLE = "libelle";
	private static final String COL_CAT_NO_CATEGORIE = "no_categorie";
	
	private static final String SQL_SELECT_CATEGORIES = "SELECT no_categorie,libelle FROM categories";

	private static final String ERREUR_CONNEXION = "Problème de connexion";
	private static final String ERREUR_SQL_SELECT = "Erreur lors de la selection en base";
	
	@Override
	public Map<Integer,Categorie> findAll() throws DALException {

		Map<Integer,Categorie> listeCategories = new HashMap<>();

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Traitement de la requete SQL
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_CATEGORIES);

				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int numCategorie = rs.getInt(COL_CAT_NO_CATEGORIE);
					String lib = rs.getString(COL_CAT_LIBELLE);

					Categorie categorie = new Categorie(lib);
					categorie.setNoCategorie(numCategorie);

					listeCategories.put(numCategorie,categorie);
				}
				
				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				throw new RequeteSQLException(ERREUR_SQL_SELECT, sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNEXION, sqle);
		}

		return listeCategories;
	}

}
