/**
 * 
 */
package org.eni.encheres.dal.articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * @author Elodie Créé le: 11 févr. 2021 Modifié le: 11 févr. 2021
 */
public class ArticleDAOImpl implements ArticleDAO {

	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) values (?,?,?,?,?,?,?)";

	/* 
	 * 
	 */
	@Override
	public void create(ArticleVendu newArticle) throws DALException {

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);

			// Valorisation des parametres du PreparedStatement
			pstmt.setString(1, newArticle.getNomArticle());
			pstmt.setString(2, newArticle.getDescription());
			pstmt.setDate(3, java.sql.Date.valueOf(newArticle.getDateDebutEncheres()));
			pstmt.setDate(4, java.sql.Date.valueOf(newArticle.getDateFinEncheres()));
			pstmt.setInt(5, newArticle.getPrixInitial());
			pstmt.setInt(6, newArticle.getVendeur().getNoUtilisateur());
			pstmt.setInt(7, newArticle.getCategorie().getNoCategorie());

			try {
				// Execution de la requete
				pstmt.executeUpdate();

				// Récupération de l'ID généré pour l'article
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					newArticle.setNoArticle(rs.getInt(1)); // Mise à jour de l'article
				}

				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				pstmt.close();
				throw new RequeteSQLException("Erreur lors de l'insertion en base", sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

	}

}
