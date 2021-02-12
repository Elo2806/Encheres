/**
 * 
 */
package org.eni.encheres.dal.articles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
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

	private static final String SQL_SELECT_ENCHERE_MAX = "SELECT e.no_article,date_enchere, montant_enchere,e.no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif "
			+ "FROM ENCHERES as e INNER JOIN (SELECT no_article, max(montant_enchere) as montant_max  "
			+ "FROM ENCHERES GROUP BY no_article) as selectMontantMax ON e.no_article = selectMontantMax.no_article "
			+ "WHERE e.montant_enchere = selectMontantMax.montant_max;";

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
	private static final String COL_UTIL_ACTIF = "actif";
	private static final String COL_ART_NO_CATEGORIE = "art.no_categorie";
	private static final String COL_CAT_LIBELLE = "cat.libelle";

	private static final String SQL_FINDALL_CATEGORIE = "SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, art.no_utilisateur, art.no_categorie,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif,cat.libelle  "
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
			pstmt.setTimestamp(3, Timestamp.valueOf(newArticle.getDateDebutEncheres()));
			pstmt.setTimestamp(4, Timestamp.valueOf(newArticle.getDateFinEncheres()));
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
							rs.getBoolean(COL_UTIL_ADMINISTRATEUR),rs.getInt(COLL_UTI_NO_UTILISATEUR),
							rs.getBoolean(COL_UTIL_ACTIF));

					ArticleVendu article = DAOFactory.creerArticle(rs.getString(COL_ART_NOM_ARTICLE),
							rs.getString(COL_ART_DESCRIPTION), rs.getTimestamp(COL_ART_DATE_DEBUT_ENCHERES).toLocalDateTime(),
							rs.getTimestamp(COL_ART_DATE_FIN_ENCHERES).toLocalDateTime(), utilisateur, categorie);
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
	public Map<Integer, ArticleVendu> updateEnchereMax(Map<Integer, ArticleVendu> articles) throws DALException {
		Map<Integer, ArticleVendu> mapArticles = new HashMap<>();
		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ENCHERE_MAX);

			try {
				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					ArticleVendu article = articles.get(rs.getInt("e.no_article"));
					
					Utilisateur utilisateur = DAOFactory.creerUtilisateur(rs.getString(COL_UTIL_PSEUDO),
							rs.getString(COL_UTIL_NOM), rs.getString(COL_UTIL_PRENOM), rs.getString(COL_UTIL_EMAIL),
							rs.getString(COL_UTIL_TELEPHONE), rs.getString(COL_UTIL_RUE),
							rs.getString(COL_UTIL_CODE_POSTAL), rs.getString(COL_UTIL_VILLE),
							rs.getString(COL_UTIL_MOT_DE_PASSE), rs.getInt(COL_UTIL_CREDIT),
							rs.getBoolean(COL_UTIL_ADMINISTRATEUR),rs.getInt(COLL_UTI_NO_UTILISATEUR),
							rs.getBoolean(COL_UTIL_ACTIF));
					Enchere enchereMax = new Enchere(rs.getTimestamp("date_enchere").toLocalDateTime(), rs.getInt("montant_enchere"), utilisateur, article);//FIXME tester la convertion
					article.setEnchereMax(enchereMax);
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
		mapArticles = articles;
		
		return mapArticles;
	}

}
