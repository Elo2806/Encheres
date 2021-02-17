package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/ServletEnchere")
public class ServletEnchere extends HttpServlet {
	private static final String PARAM_PROPOSITION = "proposition";
	private static final String APP_ENCODAGE = "UTF-8";
	private static final String ATTR_NON_AUTORISE = "nonAutorise";
	private static final String APP_ATTR_MAP_ARTICLES = "mapArticles";
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
		} else {
			request.setAttribute(ATTR_NON_AUTORISE, true);
			JOptionPane.showMessageDialog(null, "Vous n'êtes pas autorisé");
			getServletContext().getRequestDispatcher("/accueil").forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(APP_ENCODAGE);
		EnchereManager manager = EnchereManager.getInstance();

		ArticleVendu articleEnVente = null;
		int idArticle = Integer.parseInt(request.getParameter("noArticle"));
		Map<Integer,ArticleVendu> articlesEnBase = (Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		
		articleEnVente = articlesEnBase.get(idArticle);
		int meilleureOffre = articleEnVente.getEnchereMax().getMontantEnchere();
		int miseAPrix = articleEnVente.getPrixInitial();
		
		if(meilleureOffre > 0) {
			
			int prixVente = meilleureOffre
					
		} else {
			prixVente = miseAPrix
					
		}
		
			if(request.getParameter(PARAM_PROPOSITION) > prixVente {
				manager.encherir(dateEnchere, montantEnchere, utilisateur, article);;	
			}
		 
		
	
			
		}else {
			request.getParameter(PARAM_PROPOSITION) > miseAPrix alors encherir(PARAM_PROPOSITION);
		}
		
		
		

		
		
		
		
		System.out.println(request.getParameter("prix") + "prix");//TODO
		
		
		

		
//		manager.encherir(dateEnchere, montantEnchere, utilisateur, article);
		// permet d'enchérir sur un article
		getServletContext().getRequestDispatcher("/enchere").forward(request, response);
	}

}
