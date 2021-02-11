/**
 * 
 */
package org.eni.encheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eni.encheres.bo.Enchere;
import org.eni.encheres.dal.encheres.EnchereDAO;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * @author Catherine
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public class EnchereDAOimpl implements EnchereDAO {

	
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) values (?,?,?,?)";

	@Override
	public void encherir(Enchere nouvelleEnchere) throws DALException {
		
		// Connection en base
				try(Connection cnx = ConnectionProvider.getConnection()){
					
					//Traitement de la requete SQL
					try {
					cnx.setAutoCommit(false);	
					PreparedStatement pstmt = cnx.prepareStatement (INSERT_ENCHERE);
					
					// Valorisation des parametres du PreparedStatement
					pstmt.setInt(1, nouvelleEnchere.getUtilisateur().getNoUtilisateur());
					pstmt.setInt(2, nouvelleEnchere.getArticle().getNoArticle());
					pstmt.setDate(3, java.sql.Date.valueOf(nouvelleEnchere.getDateEnchere()));
					pstmt.setInt(4, nouvelleEnchere.getMontantEnchere());
					
					// Execution de la requete
					pstmt.executeUpdate();

					pstmt.close();
					
					// Tout s'est bien passé => transaction validée
					cnx.commit();
					
					} catch (SQLException sqle) {
						sqle.printStackTrace();
						// Il y a eu une probleme => transaction annulée
						cnx.rollback();
					}
				} catch (SQLException sqle){
					sqle.printStackTrace();
					throw new ConnectionException("Problème de connection", sqle);
				}

	}

}
