package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;


/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletVente")
public class ServletVente extends HttpServlet {
	
	private static final String APP_ATTR_MAP_ARTICLES = "mapArticles";
	private static final String APP_MAP_CATEGORIES = "mapCategories";
	
	private static final String SERVLET_ACCUEIL = "/ServletAccueil";
	private static final String JSP_VENTE = "/vente";
	
	private static final String SESSION_ATTR_UTILISATEUR = "utilisateur";
	
	private static final String ATTR_VILLE = "ville";
	private static final String ATTR_CODE_POSTAL = "cp";
	private static final String ATTR_RUE = "rue";
	private static final String ATTR_VENTE_CREE = "venteCree";
	
	private static final String PARAM_VILLE = ATTR_VILLE;
	private static final String PARAM_CODEPOSTAL = "codepostal";
	private static final String PARAM_RUE = ATTR_RUE;
	private static final String PARAM_DATEFIN = "datefin";
	private static final String PARAM_HEUREFIN = "heurefin";
	private static final String PARAM_DATEDEBUT = "datedebut";
	private static final String PARAM_HEUREDEBUT = "heuredebut";
	private static final String PARAM_PRIXDEPART = "prixdepart";
	private static final String PARAM_CATEGORIE = "categorie";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_ARTICLE = "article";
	
	private static final long serialVersionUID = 1L;
	private static final String APP_ENCODAGE = "UTF-8";
	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String FORMAT_HEURE = "HH:mm";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur vendeur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		// Si l'utilisateur est autorisé (compte actif ou non)
		if (vendeur.isActif()) {
			// Entrez l'adresse du vendeur par défaut
			// Récupérer l'adresse
			String rue = vendeur.getRue();
			String codePostal = vendeur.getCodePostal();
			String ville = vendeur.getVille();
			// Mettre ces variables en attribut dans request
			request.setAttribute(ATTR_RUE, rue);
			request.setAttribute(ATTR_CODE_POSTAL, codePostal);
			request.setAttribute(ATTR_VILLE, ville);

			getServletContext().getRequestDispatcher(JSP_VENTE).forward(request, response);
		}
		// TODO cas si vendeur inactif
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		boolean erreurInsertion = false,venteCree = false;
		Integer pCategorie,prixdepart;
		String article ,description,rue,codePostal,ville;
		LocalDate pDateDebut,pDateFin;
		LocalTime pHeureDebut,pHeureFin;
		LocalDateTime dateDebut, dateFin;
		Map<Integer, Categorie> mapCategorie;
		Map<Integer, ArticleVendu> mapArticles;
		ArticleVendu nouvelArticle=null;
		Categorie categorieArticle;
		Utilisateur vendeur;
		ArticleManager manager = ArticleManager.getInstance();
		
		request.setCharacterEncoding(APP_ENCODAGE);
		
		//Recuperer parametres généraux
		vendeur = (Utilisateur) request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR);
		mapCategorie = (Map<Integer, Categorie>) getServletContext().getAttribute(APP_MAP_CATEGORIES);
		mapArticles = (Map<Integer, ArticleVendu>) getServletContext().getAttribute(APP_ATTR_MAP_ARTICLES);
		
		// récupérer les paramètres a partir de la requete http
		article = request.getParameter(PARAM_ARTICLE);
		description = request.getParameter(PARAM_DESCRIPTION);
		pCategorie= Integer.parseInt(request.getParameter(PARAM_CATEGORIE));
		prixdepart = Integer.parseInt(request.getParameter(PARAM_PRIXDEPART));
		pDateDebut = LocalDate.parse(request.getParameter(PARAM_DATEDEBUT),
				DateTimeFormatter.ofPattern(FORMAT_DATE));
		 pHeureDebut = LocalTime.parse(request.getParameter(PARAM_HEUREDEBUT),
				DateTimeFormatter.ofPattern(FORMAT_HEURE));
		pDateFin = LocalDate.parse(request.getParameter(PARAM_DATEFIN),
				DateTimeFormatter.ofPattern(FORMAT_DATE));
		pHeureFin = LocalTime.parse(request.getParameter(PARAM_HEUREFIN),
				DateTimeFormatter.ofPattern(FORMAT_HEURE));
		// Besoin si différent de l'adresse du vendeur :
		rue = request.getParameter(PARAM_RUE);
		codePostal = request.getParameter(PARAM_CODEPOSTAL);
		ville = request.getParameter(PARAM_VILLE);
		
		// Creation des objets java à partir des parametres de la requete
		categorieArticle = mapCategorie.get(pCategorie);
		dateDebut = LocalDateTime.of(pDateDebut, pHeureDebut);
		dateFin = LocalDateTime.of(pDateFin, pHeureFin);
		
		try {
			nouvelArticle = manager.ajouterArticle(article, description, dateDebut, dateFin, vendeur, categorieArticle, prixdepart,rue,codePostal,ville);
		} catch (BLLException e) {
			e.printStackTrace();
			erreurInsertion = true;
		}

		// si tout s'est bien passé :
		if(!erreurInsertion) {
			mapArticles.put(nouvelArticle.getNoArticle(), nouvelArticle);
			venteCree = true;
			request.setAttribute(ATTR_VENTE_CREE, venteCree);
			getServletContext().getRequestDispatcher(SERVLET_ACCUEIL).forward(request, response);
		}else {
			getServletContext().getRequestDispatcher(JSP_VENTE).forward(request, response);
		}
		
	}

}
