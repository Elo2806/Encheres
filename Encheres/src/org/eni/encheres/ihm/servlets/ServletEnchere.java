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

	private static final String APP_ENCODAGE = "UTF-8";
	private static final String APP_ATTR_MAP_ARTICLES = "mapArticles";
	
	private static final String SESSION_ATTR_UTILISATEUR = "utilisateur";
	
	private static final String ATTR_VAINQUEUR = "vainqueur";
	private static final String ATTR_VENTE_TERMINEE = "venteTerminee";
	private static final String ATTR_MEILLEURE_ENCHERE = "meilleureEnchere";
	private static final String ATTR_ARTICLE_EN_VENTE = "articleEnVente";
	private static final String ATTR_BRAVO = "bravo";
	private static final String ATTR_ENCHERE_INSUFFISANTE = "enchereInsuffisante";
	private static final String ATTR_NON_AUTORISE = "nonAutorise";
	private static final String ATTR_ERREUR_INSERTION = "erreurInsertion";
	private static final String ATTR_VENDEUR = "vendeur";
	
	private static final String PARAM_PROPOSITION = "proposition";
	private static final String PARAM_NO_ARTICLE = "noArticle";
	private static final String PARAM_RETRAIT_EFFECTUE = "retraitEffectue";
	
	private static final String JSP_ENCHERE = "/enchere";
	private static final String JSP_ACCUEIL = "/accueil";
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int meilleureEnchere,idArticle;
		Utilisateur encherisseur;
		Map<Integer,ArticleVendu> articlesEnBase;
		ArticleVendu articleEnVente;
		EnchereManager manager = EnchereManager.getInstance();
		
		articlesEnBase =(Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		encherisseur = (Utilisateur) request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR);
		meilleureEnchere = determinerMontantEnchereADepasser(request)+1;
		idArticle = Integer.parseInt(request.getParameter(PARAM_NO_ARTICLE));
		articleEnVente = articlesEnBase.get(idArticle);
		
		//Test si la vente est terminée
		if(LocalDateTime.now().isAfter(articleEnVente.getDateFinEncheres())) {
			try {
				articleEnVente = manager.DeterminerVainqueurEnchere(articleEnVente);
			} catch (BLLException blle) {
				blle.printStackTrace();//TODO voir comment gerer cette erreur (problème lors de la modification de l'article)
			}
			request.setAttribute(ATTR_VENTE_TERMINEE, true);
			if(articleEnVente.getEnchereMax().getUtilisateur().getNoUtilisateur().equals(encherisseur.getNoUtilisateur())) {
				request.setAttribute(ATTR_VAINQUEUR, true);
			}
			if(articleEnVente.getVendeur().getNoUtilisateur().equals(encherisseur.getNoUtilisateur())) {
				request.setAttribute(ATTR_VENDEUR, true);
			}
		}
		
		// Si l'utilisateur est autorisé (compte actif ou non)
		if (encherisseur.isActif()) {
			request.setAttribute(ATTR_ARTICLE_EN_VENTE, articleEnVente);
			request.setAttribute(ATTR_MEILLEURE_ENCHERE, meilleureEnchere);
			getServletContext().getRequestDispatcher(JSP_ENCHERE).forward(request, response);
		} else {
			request.setAttribute(ATTR_NON_AUTORISE, true);
			getServletContext().getRequestDispatcher(JSP_ACCUEIL).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean retrait;
		int idArticle;
		String pageSuivante;
		Map<Integer,ArticleVendu> articlesEnBase;
		ArticleVendu articleConcerne;
		
		//Récupération de l'article concerné
		articlesEnBase = (Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		idArticle = Integer.parseInt(request.getParameter(PARAM_NO_ARTICLE));
		articleConcerne =articlesEnBase.get(idArticle);
		
		request.setCharacterEncoding(APP_ENCODAGE);
		
		retrait = Boolean.parseBoolean(request.getParameter(PARAM_RETRAIT_EFFECTUE));
		
		//test si le retrait est réalisé
		if(retrait) {
			//gestion du retrait d'article
			pageSuivante = gererRetraitArticle(articleConcerne,request);
		}else {
			//gestion de l'enchere
			pageSuivante = gererPropositionEnchere(articleConcerne,request);
		}
		
		getServletContext().getRequestDispatcher(pageSuivante).forward(request, response);
	}

	private String gererRetraitArticle(ArticleVendu articleARetire,HttpServletRequest request) {
		EnchereManager manager;
		
		manager = EnchereManager.getInstance();
		manager.retirerArticle(articleARetire);
		
		return JSP_ACCUEIL;
				
	}

	/**
	 * Méthode permettant de gérée la proposition d'enchère faites par l'utilisateur
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private String gererPropositionEnchere(ArticleVendu articleEnVente, HttpServletRequest request) {
		EnchereManager manager = EnchereManager.getInstance();
		int idArticle;
		int meilleureEnchere = determinerMontantEnchereADepasser(request);
		Enchere enchereRetournee = null;
		Boolean erreur = false;
		Integer propositionEnchere = Integer.parseInt(request.getParameter(PARAM_PROPOSITION));
		Map<Integer, Enchere> encheres = (Map<Integer, Enchere>)((Utilisateur)request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR)).getMapEncheres();
		
		idArticle = articleEnVente.getNoArticle();
		
		if(propositionEnchere > meilleureEnchere) {
			// Si une enchère a déjà été faite par l'utilisateur :
			if (encheres.get(idArticle) != null) {
				try {
					enchereRetournee = manager.modifierEnchere(LocalDateTime.now(), propositionEnchere, (Utilisateur)request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR), articleEnVente);
				} catch (BLLException blle) {
					request.setAttribute(ATTR_ERREUR_INSERTION, true);
					erreur = true;
					blle.printStackTrace();
				}
			}else {
				try {
					enchereRetournee = manager.creerEnchere(LocalDateTime.now(), propositionEnchere, (Utilisateur)request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR), articleEnVente);
				} catch (BLLException blle) {
					request.setAttribute(ATTR_ERREUR_INSERTION, true);
					erreur = true;
					blle.printStackTrace();
				}
			}
			
			if(!erreur) {
				meilleureEnchere = propositionEnchere;
				encheres.put(idArticle, enchereRetournee);
				request.setAttribute(ATTR_BRAVO, true);
				request.setAttribute(ATTR_ARTICLE_EN_VENTE, articleEnVente);
				request.setAttribute(ATTR_MEILLEURE_ENCHERE, meilleureEnchere);
				articleEnVente.setEnchereMax(enchereRetournee);
			}
			
		} else {
			request.setAttribute(ATTR_MEILLEURE_ENCHERE, meilleureEnchere);
			request.setAttribute(ATTR_ENCHERE_INSUFFISANTE, true);
		}
		
		return JSP_ENCHERE;
	}

	/**
	 * Méthode permettant de 
	 * @return
	 */
	private int determinerMontantEnchereADepasser(HttpServletRequest request) {
		ArticleVendu articleEnVente = null;
		Map<Integer,ArticleVendu> articlesEnBase = (Map<Integer,ArticleVendu>)getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		int idArticle = Integer.parseInt(request.getParameter(PARAM_NO_ARTICLE));
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
	// TODO à supprimer :
	@Override
	public void init() throws ServletException {
		System.out.println("Servlet init");
		super.init();
	}
}
