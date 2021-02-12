/**
 * 
 */
package org.eni.encheres.dal.articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * @author Elodie Créé le: 11 févr. 2021 Modifié le: 11 févr. 2021
 */
public class ArticleDAOImpl implements ArticleDAO {

	private static final String COLL_UTI_NO_UTILISATEUR = "uti.no_utilisateur";
	private static final String COL_ART_DATE_FIN_ENCHERES = "date_fin_encheres";
	private static final String COL_ART_DATE_DEBUT_ENCHERES = "date_debut_encheres";
	private static final String COL_ART_DESCRIPTION = "description";
	private static final String COL_ART_NOM_ARTICLE = "nom_article";
	private static final String COL_UTIL_ADMINISTRATEUR = "administrateur";
	private static final String COL_UTIL_CREDIT = "credit";
	private static final String COL_UTIL_MOT_DE_PASSE = "mot_de_passe";
	private static final String COL_UTIL_VILLE = "ville";
	private static final String COL_UTIL_CODE_POSTAL = "code_postal";
	private static final String COL_UTIL_RUE = "rue";
	private static final String COL_UTIL_TELEPHONE = "telephone";
	private static final String COL_UTIL_EMAIL = "email";
	private static final String COL_UTIL_PRENOM = "prenom";
	private static final String COL_UTIL_NOM = "nom";
	private static final String COL_UTIL_PSEUDO = "pseudo";
	private static final String COL_ART_NO_CATEGORIE = "art.no_categorie";
	private static final String COL_CAT_LIBELLE = "cat.libelle";
	
	private static final String SQL_FINDALL_CATEGORIE = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, art.no_utilisateur, art.no_categorie,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,cat.libelle  "
			+ "FROM ARTICLES_VENDUS as art INNER JOIN CATEGORIES as cat ON cat.no_categorie = art.no_categorie"
			+ "INNER JOIN UTILISATEUR as uti ON uti.no_utilisateur = art.no_utilisateur";
	
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

	@Override
	public Map<Integer, ArticleVendu> findAll() throws DALException {

		Map<Integer, ArticleVendu> mapArticles = new HashMap<>();

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_FINDALL_CATEGORIE);

			try {
				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					Categorie categorie = new Categorie(rs.getString(COL_CAT_LIBELLE));
					categorie.setNoCategorie(rs.getInt(COL_ART_NO_CATEGORIE));
					Utilisateur utilisateur = DAOFactory.creerUtilisateur(rs.getString(COL_UTIL_PSEUDO),
							rs.getString(COL_UTIL_NOM), rs.getString(COL_UTIL_PRENOM), rs.getString(COL_UTIL_EMAIL),
							rs.getString(COL_UTIL_TELEPHONE), rs.getString(COL_UTIL_RUE),
							rs.getString(COL_UTIL_CODE_POSTAL), rs.getString(COL_UTIL_VILLE),
							rs.getString(COL_UTIL_MOT_DE_PASSE), rs.getInt(COL_UTIL_CREDIT),
							rs.getBoolean(COL_UTIL_ADMINISTRATEUR));
					utilisateur.setNoUtilisateur(rs.getInt(COLL_UTI_NO_UTILISATEUR));

					ArticleVendu article = DAOFactory.creerArticle(rs.getString(COL_ART_NOM_ARTICLE),
							rs.getString(COL_ART_DESCRIPTION), rs.getDate(COL_ART_DATE_DEBUT_ENCHERES).toLocalDate(),
							rs.getDate(COL_ART_DATE_FIN_ENCHERES).toLocalDate(), utilisateur, categorie);
					article.setNoArticle(rs.getInt(1)); // Mise à jour de l'article

					mapArticles.put(article.getNoArticle(), article);
				}

				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				pstmt.close();
				throw new RequeteSQLException("Erreur lors de la recherche en base", sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

		return mapArticles;
	}

	@Override
	public Map<Integer, ArticleVendu> updateEnchereMax(Collection<ArticleVendu> values) throws DALException {
		Map<Integer, ArticleVendu> mapArticles = new HashMap<>();
		int articleID = 0;
		int maxEnchere = 0;
		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement("SELECT no_utilisateur,no_article,date_enchere,montant_enchere FROM ARTICLES_VENDUS WHERE ");

			try {
				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {			
					if(articleID != rs.getInt("no_article")) {
						articleID = rs.getInt("no_article");
						maxEnchere = 0;
					}
					
					if(maxEnchere > ) {
						
					}
					mapArticles.put(article.getNoArticle(), article);
				}

				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				pstmt.close();
				throw new RequeteSQLException("Erreur lors de la recherche en base", sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

		return mapArticles;
	}

}
