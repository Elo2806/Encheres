package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import org.eni.encheres.dal.exceptions.DALException;


/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletVente")
public class ServletVente extends HttpServlet {
	private static final String PARAM_VILLE = "ville";
	private static final String PARAM_CODEPOSTAL = "codepostal";
	private static final String PARAM_RUE = "rue";
	private static final String PARAM_DATEFIN = "datefin";
	private static final String PARAM_HEUREFIN = "heurefin";
	private static final String PARAM_DATEDEBUT = "datedebut";
	private static final String PARAM_HEUREDEBUT = "heuredebut";
	private static final String PARAM_PRIXDEPART = "prixdepart";
	private static final String PARAM_CATEGORIE = "categorie";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_ARTICLE = "article";
	private static final long serialVersionUID = 1L;
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
			request.setAttribute("rue", rue);
			request.setAttribute("cp", codePostal);
			request.setAttribute("ville", ville);

			getServletContext().getRequestDispatcher("/vente").forward(request, response);
		}
		// TODO cas si vendeur inactif
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur vendeur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		// récupérer les paramètres
		String article = request.getParameter(PARAM_ARTICLE);
		String description = request.getParameter(PARAM_DESCRIPTION);
		
		Categorie categorieArticle = null;
		
		int pCategorie = Integer.parseInt(request.getParameter(PARAM_CATEGORIE));

		List<Categorie> listeCategorie = (List<Categorie>) getServletContext().getAttribute("listeCategories");
		for (Categorie categorieEnCours : listeCategorie) {
			if (pCategorie== categorieEnCours.getNoCategorie()) {
				categorieArticle = categorieEnCours;
				break;
			}
		}

		Integer prixdepart = Integer.parseInt(request.getParameter(PARAM_PRIXDEPART));
		
		LocalDate pDateDebut = LocalDate.parse(request.getParameter(PARAM_DATEDEBUT),
				DateTimeFormatter.ofPattern(FORMAT_DATE));
		
		LocalTime pHeureDebut = LocalTime.parse(request.getParameter(PARAM_HEUREDEBUT),
				DateTimeFormatter.ofPattern(FORMAT_HEURE));
		
		LocalDate pDateFin = LocalDate.parse(request.getParameter(PARAM_DATEFIN),
				DateTimeFormatter.ofPattern(FORMAT_DATE));
		LocalTime pHeureFin = LocalTime.parse(request.getParameter(PARAM_HEUREFIN),
				DateTimeFormatter.ofPattern(FORMAT_HEURE));

		LocalDateTime dateDebut = LocalDateTime.of(pDateDebut, pHeureDebut);
		LocalDateTime dateFin = LocalDateTime.of(pDateFin, pHeureFin);
		
		// Besoin si différent de l'adresse du vendeur :
		String rue = request.getParameter(PARAM_RUE);
		String codePostal = request.getParameter(PARAM_CODEPOSTAL);
		String ville = request.getParameter(PARAM_VILLE);
		

		ArticleManager manager = ArticleManager.getInstance();
		try {
			manager.ajouterArticle(article, description, dateDebut, dateFin, vendeur, categorieArticle, prixdepart);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// si tout s'est bien passé :
		Boolean venteCree = true;
		request.setAttribute("booleen", venteCree);
		getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
	}

}
