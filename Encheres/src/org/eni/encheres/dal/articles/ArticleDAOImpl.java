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
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * 
 * Classe concrète permettant de mettre les méthodes permettant d'interagir avec la base de données SQL sur les objets métiers liés aux Articles.
 * 
 * @author Elodie Créé le: 11 févr. 2021
 * 
 *         Modifié le: 17 févr. 2021 par Taharqa
 */
public class ArticleDAOImpl implements ArticleDAO {
	
	private static final String COLL_RET_VILLE = "retraitVille";
	private static final String COLL_RET_CODE_POSTAL = "retraitCodePostal";
	private static final String COLL_RET_RUE = "retraitRue";

	private static final String COL_ENCH_MONTANT_ENCHERE = "montant_enchere";
	private static final String COL_ENCH_DATE_ENCHERE = "date_enchere";
	
	private static final String COL_ART_PRIX_INITIAL = "prix_initial";
	private static final String COL_ART_NO_ARTICLE = "no_article";
	private static final String COL_ART_DATE_FIN_ENCHERES = "date_fin_encheres";
	private static final String COL_ART_DATE_DEBUT_ENCHERES = "date_debut_encheres";
	private static final String COL_ART_DESCRIPTION = "description";
	private static final String COL_ART_NOM_ARTICLE = "nom_article";
	private static final String COL_ART_NO_CATEGORIE = "no_categorie";

	private static final String COL_UTIL_NO_UTILISATEUR = "no_utilisateur";
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

	private static final String COL_CAT_LIBELLE = "libelle";

	private static final String SQL_SELECT_ENCHERE_MAX = "SELECT e.no_article,date_enchere, montant_enchere,e.no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif"
			+ " FROM ENCHERES as e INNER JOIN (SELECT no_article, max(montant_enchere) as montant_max  "
			+ " FROM ENCHERES GROUP BY no_article) as selectMontantMax ON e.no_article = selectMontantMax.no_article "
			+ " INNER JOIN UTILISATEURS as u ON u.no_utilisateur = e.no_utilisateur "
			+ " WHERE e.montant_enchere = selectMontantMax.montant_max;";
	private static final String SQL_FINDALL_ARTICLES = "SELECT art.prix_initial,art.no_article,nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, art.no_utilisateur, art.no_categorie,uti.pseudo,uti.nom,uti.prenom,uti.email,uti.telephone,uti.rue,uti.code_postal,uti.ville,uti.mot_de_passe,uti.credit,uti.administrateur,uti.actif,cat.libelle,ret.rue as retraitRue, ret.code_postal as retraitCodePostal, ret.ville as retraitVille FROM ARTICLES_VENDUS as art INNER JOIN CATEGORIES as cat ON cat.no_categorie = art.no_categorie INNER JOIN UTILISATEURS as uti ON uti.no_utilisateur = art.no_utilisateur INNER JOIN RETRAITS as ret ON ret.no_article = art.no_article";
	private static final String SQL_INSERT_ARTICLE = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, no_utilisateur, no_categorie) values (?,?,?,?,?,?,?)";
	private static final String SQL_INSERT_RETRAIT = "INSERT INTO RETRAITS (no_article,rue,code_postal,ville) VALUES(?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article=?, description=?, date_debut_encheres=?, date_fin_encheres=?, prix_initial=?, no_utilisateur=?, no_categorie=? WHERE no_article=?";

	private static final String ERREUR_CONNECTION = "Problème de connection";
	private static final String ERREUR_SQL_RECHERCHE_EN_BASE = "Erreur lors de la recherche en base";
	private static final String ERREUR_SQL_INSERTION = "Erreur lors de l'insertion en base";
	private static final String ERREUR_SQL_UPDATE = "Erreur lors de la modification en base";
	
	@Override
	public ArticleVendu create(ArticleVendu newArticle) throws DALException {
		ArticleVendu updatedArticle = null;
		Retrait newRetrait;
		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Création de la transaction SQL
				cnx.setAutoCommit(false);

				// Creation du prepareStatement
				PreparedStatement pstmt = cnx.prepareStatement(SQL_INSERT_ARTICLE,
						PreparedStatement.RETURN_GENERATED_KEYS);

				// Valorisation des parametres du PreparedStatement pour l'article
				pstmt.setString(1, newArticle.getNomArticle());
				pstmt.setString(2, newArticle.getDescription());
				pstmt.setTimestamp(3, Timestamp.valueOf(newArticle.getDateDebutEncheres()));
				pstmt.setTimestamp(4, Timestamp.valueOf(newArticle.getDateFinEncheres()));
				pstmt.setInt(5, newArticle.getPrixInitial());
				pstmt.setInt(6, newArticle.getVendeur().getNoUtilisateur());
				pstmt.setInt(7, newArticle.getCategorie().getNoCategorie());

				// Execution de la requete
				pstmt.executeUpdate();

				// Récupération de l'ID généré pour l'article
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					newArticle.setNoArticle(rs.getInt(1)); // Mise à jour de l'article
				}

				// Clôture des objets
				rs.close();
				pstmt.close();

				// Valorisation des parametres du PreparedStatement pour le retrait
				newRetrait = newArticle.getRetrait();
				pstmt = cnx.prepareStatement(SQL_INSERT_RETRAIT, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, newArticle.getNoArticle());
				pstmt.setString(2, newRetrait.getRue());
				pstmt.setString(3, newRetrait.getCodePostal());
				pstmt.setString(4, newRetrait.getVille());

				// Execution de la requete
				pstmt.executeUpdate();

				// Clôture des objets
				rs.close();
				pstmt.close();

				// Confirmation de la transaction
				cnx.commit();
			} catch (SQLException sqle) {
				// Annulation de la transaction
				cnx.rollback();
				throw new RequeteSQLException(ERREUR_SQL_INSERTION, sqle);
			} finally {
				// fin de la transaction
				cnx.setAutoCommit(true);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}

		updatedArticle = newArticle;

		return updatedArticle;

	}

	@Override
	public Map<Integer, ArticleVendu> findAll() throws DALException {
		ArticleVendu article;
		Utilisateur utilisateur;
		Categorie categorie;
		Retrait retrait;
		Map<Integer, ArticleVendu> mapArticles = new HashMap<>();

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_FINDALL_ARTICLES);
			try {
				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {

					// Création des instances d'objets java
					categorie = new Categorie(rs.getString(COL_CAT_LIBELLE), rs.getInt(COL_ART_NO_CATEGORIE));
					utilisateur = new Utilisateur(rs.getString(COL_UTIL_PSEUDO), rs.getString(COL_UTIL_NOM),
							rs.getString(COL_UTIL_PRENOM), rs.getString(COL_UTIL_EMAIL),
							rs.getString(COL_UTIL_TELEPHONE), rs.getString(COL_UTIL_RUE),
							rs.getString(COL_UTIL_CODE_POSTAL), rs.getString(COL_UTIL_VILLE),
							rs.getString(COL_UTIL_MOT_DE_PASSE), rs.getInt(COL_UTIL_CREDIT),
							rs.getBoolean(COL_UTIL_ADMINISTRATEUR), rs.getInt(COL_UTIL_NO_UTILISATEUR),
							rs.getBoolean(COL_UTIL_ACTIF));
					retrait = new Retrait(rs.getString(COLL_RET_RUE), rs.getString(COLL_RET_CODE_POSTAL),
							rs.getString(COLL_RET_VILLE), null);
					article = new ArticleVendu(rs.getString(COL_ART_NOM_ARTICLE), rs.getString(COL_ART_DESCRIPTION),
							rs.getTimestamp(COL_ART_DATE_DEBUT_ENCHERES).toLocalDateTime(),
							rs.getTimestamp(COL_ART_DATE_FIN_ENCHERES).toLocalDateTime(), utilisateur, categorie,
							retrait, rs.getInt(COL_ART_NO_ARTICLE), rs.getInt(COL_ART_PRIX_INITIAL));

					// Ajout de l'article à la map
					mapArticles.put(article.getNoArticle(), article);
				}

				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				pstmt.close();
				throw new RequeteSQLException(ERREUR_SQL_RECHERCHE_EN_BASE, sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}

		mapArticles = updateEnchereMax(mapArticles);

		return mapArticles;
	}

	@Override
	public Map<Integer, ArticleVendu> updateEnchereMax(Map<Integer, ArticleVendu> articles) throws DALException {
		Map<Integer, ArticleVendu> mapArticles = new HashMap<>();
		ArticleVendu article;
		Utilisateur utilisateur;
		Enchere enchereMax;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_ENCHERE_MAX);

			try {
				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					article = articles.get(rs.getInt(COL_ART_NO_ARTICLE));
					utilisateur = new Utilisateur(rs.getString(COL_UTIL_PSEUDO), rs.getString(COL_UTIL_NOM),
							rs.getString(COL_UTIL_PRENOM), rs.getString(COL_UTIL_EMAIL),
							rs.getString(COL_UTIL_TELEPHONE), rs.getString(COL_UTIL_RUE),
							rs.getString(COL_UTIL_CODE_POSTAL), rs.getString(COL_UTIL_VILLE),
							rs.getString(COL_UTIL_MOT_DE_PASSE), rs.getInt(COL_UTIL_CREDIT),
							rs.getBoolean(COL_UTIL_ADMINISTRATEUR), rs.getInt(COL_UTIL_NO_UTILISATEUR),
							rs.getBoolean(COL_UTIL_ACTIF));

					enchereMax = new Enchere(rs.getTimestamp(COL_ENCH_DATE_ENCHERE).toLocalDateTime(),
							rs.getInt(COL_ENCH_MONTANT_ENCHERE), utilisateur, article);
					article.setEnchereMax(enchereMax);
				}

				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				pstmt.close();
				throw new RequeteSQLException(ERREUR_SQL_RECHERCHE_EN_BASE, sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}

		mapArticles = articles;

		return mapArticles;
	}

	@Override
	public void update(ArticleVendu updatedArticle) throws DALException {
		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(SQL_UPDATE);

			try {
				// Valorisation des parametres du PreparedStatement pour l'article
				pstmt.setString(1, updatedArticle.getNomArticle());
				pstmt.setString(2, updatedArticle.getDescription());
				pstmt.setTimestamp(3, Timestamp.valueOf(updatedArticle.getDateDebutEncheres()));
				pstmt.setTimestamp(4, Timestamp.valueOf(updatedArticle.getDateFinEncheres()));
				pstmt.setInt(5, updatedArticle.getPrixInitial());
				pstmt.setInt(6, updatedArticle.getVendeur().getNoUtilisateur());
				pstmt.setInt(7, updatedArticle.getCategorie().getNoCategorie());
				pstmt.setInt(8, updatedArticle.getNoArticle());

				// Execution de la requete
				pstmt.executeUpdate();
				pstmt.close();

			} catch (SQLException sqle) {
				pstmt.close();
				throw new RequeteSQLException(ERREUR_SQL_UPDATE, sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}
	}

}
