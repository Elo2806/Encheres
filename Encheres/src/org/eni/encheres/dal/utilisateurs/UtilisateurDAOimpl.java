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
	public int controleIdentifiantsExistants(String identifiant, String motDePasse) throws DALException {
		int idUtilisateur = 0;
		//Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				
			PreparedStatement pstmt = cnx.prepareStatement("SELECT no_utilisateur FROM UTILISATEURS WHERE pseudo=? AND mot_de_passe=?");
			
			// Valorisation des paramètres du PreparedStatement
			pstmt.setString(1, identifiant);
			pstmt.setString(2, motDePasse);
			
			// Execution de la requete
			ResultSet rs = pstmt.executeQuery();
			
			// Récupération de l'ID
			rs.next();
			idUtilisateur = rs.getInt("no_utilisateur");
			
			
			rs.close();
			pstmt.close();
			
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
		} catch (SQLException sqle) {
			throw new ConnectionException("Problème à la connection", sqle);
		}
		
		return idUtilisateur;
		
	}

}
