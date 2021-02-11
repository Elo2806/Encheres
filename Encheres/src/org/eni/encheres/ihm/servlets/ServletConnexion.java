package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		getServletContext().getRequestDispatcher("/connexion").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiants = request.getParameter("identifiant");
		String motDePasse = request.getParameter("motdepasse");
		// Vérifier identifiant et mots de passe
		Boolean trouve = UtilisateurManager.verifierIdMdP();
		
		//Si les identifiant/mot de passe ok :
		if (trouve) {
			getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
		} else {
			//Si les identifiant/mot de passe pas existants :
			getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		}
		
		
		
	
	}

}
