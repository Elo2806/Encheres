package org.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.ihm.exceptions.MotDePasseException;

/**
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/ServletCompte")
public class ServletCompte extends HttpServlet {
	private static final String ERREUR_GENERIQUE = "generique";
	private static final String ERREUR_IDENTIFIANT = "identifiant";
	private static final String ERREUR_MDP = "mdp";
	private static final String ATTR_ERREUR_MESSAGE = "ErreurMessage";
	private static final String SERVLET_CONNEXION = "/ServletConnexion";
	private static final String PARAM_CREATION = "creation";
	private static final String PARAM_VILLE = "ville";
	private static final String PARAM_CODE_POSTAL = "codePostal";
	private static final String PARAM_RUE = "rue";
	private static final String PARAM_TELEPHONE = "telephone";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PRENOM = "prenom";
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_PSEUDO = "pseudo";
	private static final String JSP_COMPTE = "/compte";
	private static final String PARAM_CONFIRMATION = "confirmation";
	private static final String PARAM_MDP = ERREUR_MDP;
	private static final String PARAM_SUPPRESSION = "suppression";
	private static final String PARAM_MODIFICATION = "modification";
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Boolean creation;
		Boolean modification;
		Boolean suppression;
		response.setCharacterEncoding("UTF-8");
		
		creation = Boolean.parseBoolean(request.getParameter(PARAM_CREATION));
		modification = Boolean.parseBoolean(request.getParameter(PARAM_MODIFICATION));
		suppression = Boolean.parseBoolean(request.getParameter(PARAM_SUPPRESSION));

		if (modification != null && modification) {
			if(!creation) {
				
			}
			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);

		} else if (suppression != null && suppression) {
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		
		String pseudo = request.getParameter(PARAM_PSEUDO);
		String nom = request.getParameter(PARAM_NOM);
		String prenom = request.getParameter(PARAM_PRENOM);
		String email = request.getParameter(PARAM_EMAIL);
		String telephone = request.getParameter(PARAM_TELEPHONE);
		String rue = request.getParameter(PARAM_RUE);
		String codePostal = request.getParameter(PARAM_CODE_POSTAL);
		String ville = request.getParameter(PARAM_VILLE);
		String mdp = request.getParameter(PARAM_MDP) ;
		String confirmation = request.getParameter(PARAM_CONFIRMATION);
		UtilisateurManager manager = UtilisateurManager.getInstance();
		
		try {
			controlerMdp(mdp,confirmation);
		} catch (MotDePasseException mdpe) {
			request.setAttribute(ATTR_ERREUR_MESSAGE, ERREUR_MDP);
			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);
		}
		
		try {
			manager.controleIdentifiantNewUtilisateur(pseudo,email);
		} catch (BLLException e) {
			request.setAttribute(ATTR_ERREUR_MESSAGE, ERREUR_IDENTIFIANT);
			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);
		}
		
		try {
			manager.ajouterUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
		}catch(BLLException blle) {
			request.setAttribute(ATTR_ERREUR_MESSAGE, ERREUR_GENERIQUE);
			blle.printStackTrace();
			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);
		}
		
		getServletContext().getRequestDispatcher(SERVLET_CONNEXION).forward(request, response);

	}

	private void controlerMdp(String mdp, String confirmation) throws MotDePasseException {
		if(!mdp.equals(confirmation) ) {
			throw new MotDePasseException("La confirmation du mot de passe est différente du mot de passe");
		}
	}

}
