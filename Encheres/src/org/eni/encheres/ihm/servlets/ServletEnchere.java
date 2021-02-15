package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/ServletEnchere")
public class ServletEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur encherisseur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		// Si l'utilisateur est autorisé (compte actif ou non)
		if (encherisseur.isActif()) {
			getServletContext().getRequestDispatcher("/enchere").forward(request, response);
		}
//TODO cas si encherisseur inactif
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// permet d'enchérir sur un article
		getServletContext().getRequestDispatcher("/enchere").forward(request, response);
	}

}
