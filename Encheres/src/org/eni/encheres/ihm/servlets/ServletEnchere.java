package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
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
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.ihm.exceptions.MotDePasseException;

/**
 * Servlet implementation class ServletEnchere
 */
@WebServlet("/ServletEnchere")
public class ServletEnchere extends HttpServlet {
	private static final String ATTR_MEILLEURE_ENCHERE = "meilleureEnchere";
	private static final String ATTR_ARTICLE_EN_VENTE = "articleEnVente";
	private static final String ATTR_BRAVO = "bravo";
	private static final String ATTR_ENCHERE_INSUFFISANTE = "enchereInsuffisante";
	private static final String PARAM_PROPOSITION = "proposition";
	private static final String APP_ENCODAGE = "UTF-8";
	private static final String ATTR_NON_AUTORISE = "nonAutorise";
	private static final String ATTR_ERREUR_INSERTION = "erreurInsertion";
	private static final String APP_ATTR_MAP_ARTICLES = "mapArticles";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Utilisateur encherisseur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int idArticle = Integer.parseInt(request.getParameter("noArticle"));
		Map<Integer,ArticleVendu> articlesEnBase = (Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		ArticleVendu articleEnVente = articlesEnBase.get(idArticle);
		int meilleureEnchere = determinerMontantEnchereADepasser(request);
		// Si l'utilisateur est autorisé (compte actif ou non)
		if (encherisseur.isActif()) {
			request.setAttribute(ATTR_ARTICLE_EN_VENTE, articleEnVente);
			request.setAttribute(ATTR_MEILLEURE_ENCHERE, meilleureEnchere);
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
		int idArticle = Integer.parseInt(request.getParameter("noArticle"));
		int meilleureEnchere = determinerMontantEnchereADepasser(request);
		Enchere enchereRetournee = null;
		Boolean erreur = false;
//		int idArticle = Integer.parseInt(request.getParameter("noArticle"));
//		ArticleVendu articleEnVente = null;
		Map<Integer,ArticleVendu> articlesEnBase = (Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		ArticleVendu articleEnVente = articlesEnBase.get(idArticle);
//		int meilleureOffre = articleEnVente.getEnchereMax().getMontantEnchere();
//		int miseAPrix = articleEnVente.getPrixInitial();
//		int prixVente = 0;
//		 
//		
//		if(meilleureOffre > 0) {
//			prixVente = meilleureOffre;
//		} else {
//			prixVente = miseAPrix;
//		}
		Integer propositionEnchere = Integer.parseInt(request.getParameter(PARAM_PROPOSITION));
		Map<Integer, Enchere> encheres = (Map<Integer, Enchere>)((Utilisateur)request.getSession().getAttribute("utilisateur")).getMapEncheres();
		System.out.println(encheres);
		if(propositionEnchere > meilleureEnchere) {
			
			System.out.println(encheres.get(idArticle) + " : dans liste encheres"); // TODO
			// Si une enchère a déjà été faite par l'utilisateur :
			if (encheres.get(idArticle) != null) {
				try {
					enchereRetournee = manager.modifierEnchere(LocalDateTime.now(), propositionEnchere, (Utilisateur)request.getSession().getAttribute("utilisateur"), articleEnVente);
				} catch (BLLException blle) {
					request.setAttribute(ATTR_ERREUR_INSERTION, true);
					erreur = true;
					blle.printStackTrace();
				}
			}else {
				try {
					enchereRetournee = manager.creerEnchere(LocalDateTime.now(), propositionEnchere, (Utilisateur)request.getSession().getAttribute("utilisateur"), articleEnVente);
				} catch (BLLException blle) {
					request.setAttribute(ATTR_ERREUR_INSERTION, true);
					erreur = true;
					blle.printStackTrace();
				}
			}
			if(!erreur) {
				System.out.println(meilleureEnchere);
				encheres.put(idArticle, enchereRetournee);
				request.setAttribute(ATTR_BRAVO, true);
				request.setAttribute(ATTR_ARTICLE_EN_VENTE, articleEnVente);
				request.setAttribute(ATTR_MEILLEURE_ENCHERE, meilleureEnchere);
				articleEnVente.setEnchereMax(enchereRetournee);
			}
			
		} else {
			request.setAttribute(ATTR_ENCHERE_INSUFFISANTE, true);
		}
		
		getServletContext().getRequestDispatcher("/enchere").forward(request, response);
	}

	/**
	 * Méthode permettant de 
	 * @return
	 */
	private int determinerMontantEnchereADepasser(HttpServletRequest request) {
		ArticleVendu articleEnVente = null;
		Map<Integer,ArticleVendu> articlesEnBase = (Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		int idArticle = Integer.parseInt(request.getParameter("noArticle"));
		articleEnVente = articlesEnBase.get(idArticle);
		
		int meilleureOffre = articleEnVente.getEnchereMax().getMontantEnchere();
		int miseAPrix = articleEnVente.getPrixInitial();
		int meilleureEnchere = 0;
		if(meilleureOffre > 0) {
			meilleureEnchere = meilleureOffre;
		} else {
			meilleureEnchere = miseAPrix;
		}
		return meilleureEnchere;
	}
	
	// TODO à supprimer :
	@Override
	public void destroy() {
		System.out.println("Servlet détruite");
		super.destroy();
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet init");
		super.init();
	}
}
