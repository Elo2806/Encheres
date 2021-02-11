package org.eni.encheres.dal.utilisateurs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eni.encheres.bo.Utilisateur;
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

	private static final String SQL_CREATE = "INSERT INTO Utilisateurs (no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif) VALUES (default,?,?,?,?,?,?,?,?,?,?,FALSE,TRUE)";

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
				prepare = cnx.prepareStatement("SELECT no_utilisateur FROM Utilisateurs WHERE pseudo=?");
				prepare.setString(1, pseudo);
				rs = prepare.executeQuery();
				if (rs.next()) {
					throw new UtilisateurExistantException() ;
				}

				//Test existence de l'adresse email
				prepare = cnx.prepareStatement("SELECT no_utilisateur FROM Utilisateurs WHERE email=?");
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
				
			PreparedStatement pstmt = cnx.prepareStatement("SELECT no_utilisateur,pseudo,nom,prenom,email,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur,actif FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?");
			
			// Valorisation des paramètres du PreparedStatement
			pstmt.setString(1, pIdentifiant);
			pstmt.setString(2, pMotDePasse);
			
			// Execution de la requete
			ResultSet rs = pstmt.executeQuery();
			
			// Récupération de l'ID
			if (rs.next()) {
				int noUtilisateur = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("ville");
				String motDePasse = rs.getString("mot_de_passe");
				int credit = rs.getInt("credit");
				boolean administrateur = rs.getBoolean("administrateur");
				boolean actif = rs.getBoolean("actif");
				
				utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
				
				utilisateur.setNoUtilisateur(noUtilisateur);
				utilisateur.setActif(actif);
				
			}
			
			
			rs.close();
			pstmt.close();
			
			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors des selections en base", sqle);
			}
			
		} catch (SQLException sqle) {
			throw new ConnectionException("Problème à la connection", sqle);
		}
		
		if (utilisateur == null) {
			throw new UtilisateurInexistantException("L'utilisateur n'existe pas");
		}
		
		return utilisateur;
		
	}

}
