package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Si "Se souvenir de moi" avait été coché, il faut renseigner les 2 champs:
//		if (request.getParameter("memoriser") cohé avant) {
//			// Récupérer l'identifiant de l'utilisateur...
//			// ... et l'insérer dans le champs
//			// Récupérer le mot de passe de l'utilisateur
//			// ... et l'insérer dans le champs
//		}
		
		getServletContext().getRequestDispatcher("/connexion").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiants = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motdepasse");
		
		// Vérifier identifiant et mots de passe
		UtilisateurManager manager = UtilisateurManager.getInstance();
		int noUtilisateur = 0;
		try {
			noUtilisateur = manager.verifierIdMdP(identifiants, motDePasse);
		} catch (BLLException blle) {
			blle.printStackTrace();
		}
		
		//Si les identifiant/mot de passe ok :
		if (noUtilisateur != 0) {
			getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
		} else {
			//Si les identifiant/mot de passe pas existants :
			getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		}
		
		
		
	
	}

}
