/**
 * 
 */
package org.eni.encheres.dal.encheres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * @author Catherine Créé le: 11 févr. 2021 Modifié le: 11 févr. 2021
 */
public class EnchereDAOimpl implements EnchereDAO {

	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";

	@Override
	public void encherir(Enchere nouvelleEnchere) throws DALException {

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = null;
			int nbLignes = 0;

			// Traitement de la requete SQL
			try {
				cnx.setAutoCommit(false);
				pstmt = cnx.prepareStatement(INSERT_ENCHERE);

				// Valorisation des parametres du PreparedStatement
				pstmt.setInt(1, nouvelleEnchere.getUtilisateur().getNoUtilisateur());
				pstmt.setInt(2, nouvelleEnchere.getArticle().getNoArticle());
				pstmt.setDate(3, java.sql.Date.valueOf(nouvelleEnchere.getDateEnchere()));
				pstmt.setInt(4, nouvelleEnchere.getMontantEnchere());

				// Execution de la requete
				nbLignes = pstmt.executeUpdate();
				
				if (nbLignes == 1) {
					// Tout s'est bien passé => transaction validée
					cnx.commit(); 
				} else {
					// Il y a eu une probleme => transaction annulée
					cnx.rollback();
					throw new DALException("Problème insertion dans méthode encherir (tentative d'insertion d'aucune ou de plusieurs lignes)");
				}

			} catch (SQLException sqle) {
				throw new DALException("Problème méthode encherir", sqle);

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
