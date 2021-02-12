package org.eni.encheres.dal.utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.DAOFactory;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.exceptions.UtilisateurExistantException;
import org.eni.encheres.dal.exceptions.UtilisateurInexistantException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;
/**
 * 
 * Cette classe permet de centraliser les méthodes d'intéraction avec le système de persistance
 * 
 * @author Taharqa
 * Créé le: 11 févr. 2021
 * Mofifié le: 11 févr. 2021
 */
public class UtilisateurDAOimpl implements UtilisateurDAO {

	private static final String COL_ACTIF = "actif";
	private static final String COL_ADMINISTRATEUR = "administrateur";
	private static final String COL_CREDIT = "credit";
	private static final String COL_MOT_DE_PASSE = "mot_de_passe";
	private static final String COL_VILLE = "ville";
	private static final String COL_CODE_POSTAL = "code_postal";
	private static final String COL_RUE = "rue";
	private static final String COL_TELEPHONE = "telephone";
	private static final String COL_EMAIL = "email";
	private static final String COL_PRENOM = "prenom";
	private static final String COL_NOM = "nom";
	private static final String COL_PSEUDO = "pseudo";
	private static final String COL_NO_UTILISATEUR = "no_utilisateur";
	
	private static final String SQL_SELECT_BY_PSEUDO_AND_MOT_DE_PASSE = "SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?";
	private static final String SQL_SELECT_BY_EMAIL = "SELECT no_utilisateur FROM Utilisateurs WHERE email=?";
	private static final String SQL_SELECT_BY_PSEUDO = "SELECT no_utilisateur FROM Utilisateurs WHERE pseudo=?";
	private static final String SQL_CREATE = "INSERT INTO Utilisateurs (pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif) VALUES (?,?,?,?,?,?,?,?,?,?,0,1)";

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
				throw new RequeteSQLException("Erreur lors de l'insertion en base", sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException("Erreur connection", sqle);
		}

		//Mise à jour de l'utilisateur
		updatedUtilisateur = newUtilisateur;

		return updatedUtilisateur;
	}

	@Override
	public void controleUtilisateurExistence(String pseudo, String email) throws DALException {
		PreparedStatement prepare;
		ResultSet rs;
		
		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			// Traitement de la requete SQL
			try {
				//Test existence du pseudo
				prepare = cnx.prepareStatement(SQL_SELECT_BY_PSEUDO);
				prepare.setString(1, pseudo);
				rs = prepare.executeQuery();
				if (rs.next()) {
					throw new UtilisateurExistantException() ;
				}

				//Test existence de l'adresse email
				prepare = cnx.prepareStatement(SQL_SELECT_BY_EMAIL);
				prepare.setString(1,email);
				rs = prepare.executeQuery();
				if (rs.next()) {
					throw new UtilisateurExistantException() ;
				}
				
				rs.close();
				prepare.close();
			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors des selections en base", sqle);
			}

		} catch (SQLException sqle) {
			throw new ConnectionException("Erreur connection", sqle);
		}
		
	}

	@Override
	public Utilisateur controleIdentifiantsExistants(String pIdentifiant, String pMotDePasse) throws DALException {
		Utilisateur utilisateur = null;
		//Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				
			PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_PSEUDO_AND_MOT_DE_PASSE);
			
			// Valorisation des paramètres du PreparedStatement
			pstmt.setString(1, pIdentifiant);
			pstmt.setString(2, pMotDePasse);
			
			// Execution de la requete
			ResultSet rs = pstmt.executeQuery();
			
			// Récupération de l'ID
			if (rs.next()) {
				int noUtilisateur = rs.getInt(COL_NO_UTILISATEUR);
				String pseudo = rs.getString(COL_PSEUDO);
				String nom = rs.getString(COL_NOM);
				String prenom = rs.getString(COL_PRENOM);
				String email = rs.getString(COL_EMAIL);
				String telephone = rs.getString(COL_TELEPHONE);
				String rue = rs.getString(COL_RUE);
				String codePostal = rs.getString(COL_CODE_POSTAL);
				String ville = rs.getString(COL_VILLE);
				String motDePasse = rs.getString(COL_MOT_DE_PASSE);
				int credit = rs.getInt(COL_CREDIT);
				boolean administrateur = rs.getBoolean(COL_ADMINISTRATEUR);
				boolean actif = rs.getBoolean(COL_ACTIF);
				
				utilisateur = DAOFactory.creerUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur, noUtilisateur, actif);
				
			}
			
			
			rs.close();
			pstmt.close();
			
			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors des selections en base", sqle);
			}
			
		} catch (SQLException sqle) {
			throw new ConnectionException("Problème de connection", sqle);
		}
		
		if (utilisateur == null) {
			throw new UtilisateurInexistantException("L'utilisateur n'existe pas");
		}
		
		return utilisateur;
		
	}
	
}
