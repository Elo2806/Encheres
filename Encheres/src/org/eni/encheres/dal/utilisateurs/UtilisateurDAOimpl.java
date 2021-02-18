package org.eni.encheres.dal.utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.exceptions.UtilisateurExistantException;
import org.eni.encheres.dal.exceptions.UtilisateurInexistantException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * 
 * Cette classe permet de centraliser les méthodes d'intéraction avec le système
 * de persistance
 * 
 * @author Taharqa Créé le: 11 févr. 2021 Mofifié le: 11 févr. 2021
 */
public class UtilisateurDAOimpl implements UtilisateurDAO {

	private static final String COLL_ART_NO_ARTICLE = "no_article";
	private static final String COL_ENC_MONTANT_ENCHERE = "montant_enchere";
	private static final String COL_ENC_DATE_ENCHERE = "date_enchere";

	private static final String COL_ART_DATE_FIN_ENCHERES = "date_fin_encheres";
	private static final String COL_ART_DATE_DEBUT_ENCHERES = "date_debut_encheres";
	private static final String COL_ART_DESCRIPTION = "description";
	private static final String COL_ART_NOM_ARTICLE = "nom_article";
	private static final String COL_ART_NO_CATEGORIE = "no_categorie";

	private static final String COL_CAT_LIBELLE = "libelle";

	private static final String COL_UTI_ACTIF = "actif";
	private static final String COL_UTI_ADMINISTRATEUR = "administrateur";
	private static final String COL_UTI_CREDIT = "credit";
	private static final String COL_UTI_MOT_DE_PASSE = "mot_de_passe";
	private static final String COL_UTI_VILLE = "ville";
	private static final String COL_UTI_CODE_POSTAL = "code_postal";
	private static final String COL_UTI_RUE = "rue";
	private static final String COL_UTI_TELEPHONE = "telephone";
	private static final String COL_UTI_EMAIL = "email";
	private static final String COL_UTI_PRENOM = "prenom";
	private static final String COL_UTI_NOM = "nom";
	private static final String COL_UTI_PSEUDO = "pseudo";
	private static final String COL_UTI_NO_UTILISATEUR = "no_utilisateur";

	private static final String SQL_SELECT_BY_MOT_DE_PASSE = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif FROM UTILISATEURS WHERE mot_de_passe=? AND supprime=0";
	private static final String SQL_SELECT_BY_EMAIL = "SELECT no_utilisateur FROM Utilisateurs WHERE email=?";
	private static final String SQL_SELECT_BY_PSEUDO = "SELECT no_utilisateur FROM Utilisateurs WHERE pseudo=?";
	private static final String SQL_CREATE = "INSERT INTO Utilisateurs (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif) VALUES (?,?,?,?,?,?,?,?,?,?,0,1)";
	private static final String SQL_UPDATE = "UPDATE Utilisateurs SET pseudo=?,nom=?,prenom=?,email=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=?,actif=? WHERE no_utilisateur=?";
	private static final String SQL_UPDATE_SUPPRIME = "UPDATE Utilisateurs SET supprime=? WHERE no_utilisateur=?";
	private static final String SQL_SELECT_ENCHERES_BY_USER = "SELECT enc.no_utilisateur, enc.no_article, enc.date_enchere, enc.montant_enchere, art.nom_article,art.description,art.date_debut_encheres,art.date_fin_encheres,art.prix_initial,art.prix_vente,art.no_categorie,cat.libelle "
			+ "FROM ENCHERES as enc INNER JOIN ARTICLES_VENDUS as art ON art.no_article =  enc.no_article "
			+ "INNER JOIN CATEGORIES as cat ON cat.no_categorie = art.no_categorie "
			+ "WHERE enc.no_utilisateur=? AND art.date_fin_encheres > ?";

	private static final String ERREUR_SQL_INSERT = "Erreur lors de l'insertion en base";
	private static final String ERREUR_SQL_SELECT = "Erreur lors des selections en base";
	private static final String ERREUR_UTILISATEUR_INEXISTANT = "L'utilisateur n'existe pas";
	private static final String ERREUR_CONNECTION = "Problème de connection";

	@Override
	public Utilisateur create(Utilisateur newUtilisateur) throws DALException {
		Utilisateur updatedUtilisateur;
		PreparedStatement prepare;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Traitement de la requete SQL
			try {
				prepare = cnx.prepareStatement(SQL_CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
				prepare.setString(1, newUtilisateur.getPseudo());
				prepare.setString(2, newUtilisateur.getNom());
				prepare.setString(3, newUtilisateur.getPrenom());
				prepare.setString(4, newUtilisateur.getEmail());
				prepare.setString(5, newUtilisateur.getTelephone());
				prepare.setString(6, newUtilisateur.getRue());
				prepare.setString(7, newUtilisateur.getCodePostal());
				prepare.setString(8, newUtilisateur.getVille());
				prepare.setString(9, newUtilisateur.getMotDePasse());
				prepare.setInt(10, newUtilisateur.getCredit());
				prepare.executeUpdate();

				ResultSet generatedKeys = prepare.getGeneratedKeys();
				if (generatedKeys.next()) {
					newUtilisateur.setNoUtilisateur(generatedKeys.getInt(1));
				}

				generatedKeys.close();
				prepare.close();

			} catch (SQLException sqle) {
				throw new RequeteSQLException(ERREUR_SQL_INSERT, sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}

		// Mise à jour de l'utilisateur
		updatedUtilisateur = newUtilisateur;

		return updatedUtilisateur;
	}

	@Override
	public void controleUtilisateurExistence(String pseudo, String email) throws DALException {

		// Test existence du pseudo
		controlePseudoExistence(pseudo);

		// Test existence de l'adresse email
		controleEmailExistence(email);

	}

	@Override
	public void controlePseudoExistence(String pseudo) throws DALException {
		PreparedStatement prepare;
		ResultSet rs;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Traitement de la requete SQL
			try {
				// Test existence du pseudo
				prepare = cnx.prepareStatement(SQL_SELECT_BY_PSEUDO);
				prepare.setString(1, pseudo);
				rs = prepare.executeQuery();
				if (rs.next()) {
					throw new UtilisateurExistantException();
				}

				rs.close();
				prepare.close();
			} catch (SQLException sqle) {
				throw new RequeteSQLException(ERREUR_SQL_SELECT, sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}

	}

	@Override
	public void controleEmailExistence(String email) throws DALException {
		PreparedStatement prepare;
		ResultSet rs;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Traitement de la requete SQL
			try {

				// Test existence de l'adresse email
				prepare = cnx.prepareStatement(SQL_SELECT_BY_EMAIL);
				prepare.setString(1, email);
				rs = prepare.executeQuery();
				if (rs.next()) {
					throw new UtilisateurExistantException();
				}

				rs.close();
				prepare.close();
			} catch (SQLException sqle) {
				throw new RequeteSQLException(ERREUR_SQL_SELECT, sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}
	}

	@Override
	public Utilisateur controleIdentifiantsExistants(String pIdentifiant, String pMotDePasse) throws DALException {
		Utilisateur utilisateur = null;
		Map<Integer, Enchere> mapEnchere;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {

				// Début de la transaction
				cnx.setAutoCommit(false);

				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_MOT_DE_PASSE);

				// Valorisation des paramètres du PreparedStatement
				pstmt.setString(1, pMotDePasse);

				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				// Récupération de l'ID
				while (rs.next()) {
					int noUtilisateur = rs.getInt(COL_UTI_NO_UTILISATEUR);
					String pseudo = rs.getString(COL_UTI_PSEUDO);
					String nom = rs.getString(COL_UTI_NOM);
					String prenom = rs.getString(COL_UTI_PRENOM);
					String email = rs.getString(COL_UTI_EMAIL);
					String telephone = rs.getString(COL_UTI_TELEPHONE);
					String rue = rs.getString(COL_UTI_RUE);
					String codePostal = rs.getString(COL_UTI_CODE_POSTAL);
					String ville = rs.getString(COL_UTI_VILLE);
					String motDePasse = rs.getString(COL_UTI_MOT_DE_PASSE);
					int credit = rs.getInt(COL_UTI_CREDIT);
					boolean administrateur = rs.getBoolean(COL_UTI_ADMINISTRATEUR);
					boolean actif = rs.getBoolean(COL_UTI_ACTIF);

					if ((pIdentifiant.equals(pseudo)) || (pIdentifiant.equals(email))) {
						utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
								motDePasse, credit, administrateur, noUtilisateur, actif);
						break;
					}
				}

				if (utilisateur != null) {

					mapEnchere = utilisateur.getMapEncheres();

					pstmt = cnx.prepareStatement(SQL_SELECT_ENCHERES_BY_USER);

					// Valorisation des paramètres du PreparedStatement
					pstmt.setInt(1, utilisateur.getNoUtilisateur());
					pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));

					// Execution de la requete
					rs = pstmt.executeQuery();

					// Récupération des encheres de l'utilisateur en cours
					while (rs.next()) {
						Categorie categorie = new Categorie(rs.getString(COL_CAT_LIBELLE),
								rs.getInt(COL_ART_NO_CATEGORIE));
						ArticleVendu article = new ArticleVendu(rs.getString(COL_ART_NOM_ARTICLE),
								rs.getString(COL_ART_DESCRIPTION),
								rs.getTimestamp(COL_ART_DATE_DEBUT_ENCHERES).toLocalDateTime(),
								rs.getTimestamp(COL_ART_DATE_FIN_ENCHERES).toLocalDateTime(), null, categorie,
								rs.getInt(COLL_ART_NO_ARTICLE));// vendeur a null pour limiter la taille de l'objet
						Enchere enchere = new Enchere(rs.getTimestamp(COL_ENC_DATE_ENCHERE).toLocalDateTime(),
								rs.getInt(COL_ENC_MONTANT_ENCHERE), utilisateur, article);
						mapEnchere.put(article.getNoArticle(), enchere);
					}

					// Validation de la transaction
					cnx.commit();
				}

				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				// Annulation de la correction
				cnx.rollback();
				throw new RequeteSQLException(ERREUR_SQL_SELECT, sqle);
			} finally {
				// fin de la transaction
				cnx.setAutoCommit(true);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException(ERREUR_CONNECTION, sqle);
		}

		if (utilisateur == null) {
			throw new UtilisateurInexistantException(ERREUR_UTILISATEUR_INEXISTANT);
		}

		return utilisateur;

	}

	@Override
	public Utilisateur update(Utilisateur utilisateurAModifier) throws DALException {
		Utilisateur modifiedUtilisateur;
		PreparedStatement pstmt;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Traitement de la requete SQL
			try {
				pstmt = cnx.prepareStatement(SQL_UPDATE);
				pstmt.setString(1, utilisateurAModifier.getPseudo());
				pstmt.setString(2, utilisateurAModifier.getNom());
				pstmt.setString(3, utilisateurAModifier.getPrenom());
				pstmt.setString(4, utilisateurAModifier.getEmail());
				pstmt.setString(5, utilisateurAModifier.getTelephone());
				pstmt.setString(6, utilisateurAModifier.getRue());
				pstmt.setString(7, utilisateurAModifier.getCodePostal());
				pstmt.setString(8, utilisateurAModifier.getVille());
				pstmt.setString(9, utilisateurAModifier.getMotDePasse());
				pstmt.setInt(10, utilisateurAModifier.getCredit());
				pstmt.setBoolean(11, utilisateurAModifier.isAdministrateur());
				pstmt.setBoolean(12, utilisateurAModifier.isActif());
				pstmt.setInt(13, utilisateurAModifier.getNoUtilisateur());
				pstmt.executeUpdate();

				pstmt.close();
			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors de la mise à jour en base", sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException("Erreur connection", sqle);
		}

		// Mise à jour de l'utilisateur
		modifiedUtilisateur = utilisateurAModifier;

		return modifiedUtilisateur;

	}

	@Override
	public Utilisateur updateSupprime(Utilisateur utilisateurASupprimer) throws DALException {

		PreparedStatement pstmt;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Traitement de la requete SQL
			try {
				pstmt = cnx.prepareStatement(SQL_UPDATE_SUPPRIME);
				pstmt.setBoolean(1, utilisateurASupprimer.isSupprime());
				pstmt.setInt(2, utilisateurASupprimer.getNoUtilisateur());
				pstmt.executeUpdate();

				pstmt.close();
			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors de la mise à jour en base", sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException("Erreur connection", sqle);
		}

		// Mise à jour de l'utilisateur
		Utilisateur modifiedUtilisateur = utilisateurASupprimer;

		return modifiedUtilisateur;
	}

}
