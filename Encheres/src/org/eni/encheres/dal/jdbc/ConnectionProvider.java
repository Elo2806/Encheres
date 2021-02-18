package org.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 
 * La classe utilitaire permettant d'accèder au pool de connexions.
 * 
 * @author Taharqa
 * Créé le: 09 févr. 2021
 * 
 */
public abstract class ConnectionProvider {

	private static final String POOL_CONNEXION_NAME = "jdbc/poolEncheres";
	private static final String RACINE_JNDI = "java:comp/env/";
	
	private static DataSource dataSource;
	
	/**
	 * Cette méthode retourne une connection opérationnelle issue du pool de connexion
	 * vers la base de données. 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException
	{
		Connection cnx = null;
		
		// Si le pool n'est pas connu, le récupérer
		if (dataSource == null) {

			Context annuaire = null;
			try {
				// Obtenir l'annuaire JNDI (annuaire de ressources)
				annuaire = new InitialContext();

				// Obtenir le pool de connexions
				dataSource = (DataSource) annuaire.lookup(RACINE_JNDI + POOL_CONNEXION_NAME);

			} catch (NamingException ne) {
				System.err.println("Impossible de trouver le pool de connexions.");
				ne.printStackTrace();
			}

		}

		// Obtenir une connexion à la base de données
		try {
			cnx = dataSource.getConnection(); // le pool nous prete une connexion disponible

		} catch (NullPointerException | SQLException e) {
			// Journaliser
			System.err.println("Impossible d'obtenir une connexion.");
			e.printStackTrace();
			// Laisser passer l'exception pour la couche au-dessus
			throw e;
		}

		return cnx;
	}
}