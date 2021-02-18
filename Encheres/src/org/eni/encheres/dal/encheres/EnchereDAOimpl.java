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
 * 
 * Classe concrète permettant de mettre les méthodes permettant d'interagir avec la base de données SQL sur les objets métiers liés aux Enchères.
 * 
 * @author Catherine Créé le: 11 févr.
 */
public class EnchereDAOimpl implements EnchereDAO {
	
	private static final String SQL_UPDATE_ENCHERE = "UPDATE Encheres SET date_enchere=?,montant_enchere=? WHERE no_utilisateur=? AND no_article=?";
	private static final String SQL_INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";

	private static final String ERREUR_CONNEXION = "Erreur de connexion";
	private static final String ERREUR_INSERT = "Erreur lors de l'insertion en base";
	private static final String ERREUR_UPDATE = "Erreur lors de la mise à jour en base";
	
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
				throw new RequeteSQLException(ERREUR_INSERT, sqle);
			} finally {
				if (pstmt != null) {
					pstmt.close();
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNEXION, sqle);
		}

	}

	@Override
	public void update(Enchere enchereAModifier) throws DALException {
		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = null;

			// Traitement de la requete SQL
			try {
				pstmt = cnx.prepareStatement(SQL_UPDATE_ENCHERE);

				// Valorisation des parametres du PreparedStatement
				pstmt.setTimestamp(1, Timestamp.valueOf(enchereAModifier.getDateEnchere()));
				pstmt.setInt(2, enchereAModifier.getMontantEnchere());
				pstmt.setInt(3, enchereAModifier.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(4, enchereAModifier.getArticle().getNoArticle());

				// Execution de la requete
				pstmt.executeUpdate();

			} catch (SQLException sqle) {
				throw new RequeteSQLException(ERREUR_UPDATE, sqle);
			} finally {
				if (pstmt != null) {
					pstmt.close();
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNEXION, sqle);
			
		}
		
	}
		
}
