/**
 * 
 */
package org.eni.encheres.dal.encheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * @author Catherine Créé le: 11 févr. 2021 Modifié le: 11 févr. 2021
 */
public class EnchereDAOimpl implements EnchereDAO {

	private static final String SQL_INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";

	@Override
	public void create(Enchere newEnchere) throws DALException {

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = null;

			// Traitement de la requete SQL
			try {
				pstmt = cnx.prepareStatement(SQL_INSERT_ENCHERE);

				// Valorisation des parametres du PreparedStatement
				pstmt.setInt(1, newEnchere.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(2, newEnchere.getArticle().getNoArticle());
				pstmt.setTimestamp(3, Timestamp.valueOf(newEnchere.getDateEnchere()));
				pstmt.setInt(4, newEnchere.getMontantEnchere());

				// Execution de la requete
				pstmt.executeUpdate();

			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors de l'insertion en base", sqle);
			} finally {
				if (pstmt != null) {
					pstmt.close();
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

	}
		
}
