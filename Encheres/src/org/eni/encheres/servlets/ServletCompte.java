package org.eni.encheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/ServletCompte")
public class ServletCompte extends HttpServlet {
	private static final String PARAM_CONFIRMATION = "confirmation";
	private static final String PARAM_MDP = "mdp";
	private static final String PARAM_SUPPRESSION = "suppression";
	private static final String PARAM_MODIFICATION = "modification";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		Boolean modification;
		Boolean suppression;
		
		
		modification = Boolean.parseBoolean(request.getParameter(PARAM_MODIFICATION));
		suppression = Boolean.parseBoolean(request.getParameter(PARAM_SUPPRESSION));

		if (modification != null && modification) {
			
			getServletContext().getRequestDispatcher("/compte").forward(request, response);

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
		

		String mdp = request.getParameter(PARAM_MDP) ;
		String confirmation = request.getParameter(PARAM_CONFIRMATION);
		
		controlerMdp(mdp,confirmation);
		
		
		
		
/*	Contrôle 
mdp + confirm = identique
 * 
 * Contrôle base de donnée
 	pseudo  et email unique
	
	Controle formulaire
	code postal entre 1000 et 99999
	numéro tel nom: 2 lettres
	
	
	*/

	}

	private void controlerMdp(String mdp, String confirmation) {
		if(mdp.equals(confirmation) ) {
			
		}
		
	}

}
