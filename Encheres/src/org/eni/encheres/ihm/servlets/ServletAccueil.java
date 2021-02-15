package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.ihm.exceptions.FiltreInexistantException;

import static org.eni.encheres.ihm.servlets.TypeFiltre.*;
import static org.eni.encheres.ihm.servlets.Filtre.*;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {
	private static final String ATTR_MAP_ARTICLES_AFFICHES = "mapArticlesAffiches";
	private static final String APP_ATTR_MAP_ARTICLES = "mapArticles";
	private static final String JSP_ACCUEIL = "/accueil";
	private static final String CST_TYPE_VENTE = "vente";
	private static final String CST_TYPE_ACHAT = "achat";
	private static final String ATTR_MAP_CATEGORIES = "mapCategories";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Integer, ArticleVendu> articles = (Map<Integer, ArticleVendu>) getServletContext()
				.getAttribute(APP_ATTR_MAP_ARTICLES);
		request.setAttribute(ATTR_MAP_ARTICLES_AFFICHES, articles);
		getServletContext().getRequestDispatcher(JSP_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<Integer, Categorie> mapCategorie;
		Categorie categorieFiltre;
		int categorieId;
		String typeFiltre;
		boolean encheresOuvertes, encheresEnCours, encheresRemportes;
		boolean ventesEnCours, ventesNonDebutes, ventesTerminees;
		Map<Integer, ArticleVendu> mapArticleAffiche = new HashMap<>();
		List<Filtre> listFiltres = new ArrayList<>();
		Utilisateur utilisateur;
		
		// Encodage de la reponse http en utf-8
		request.setCharacterEncoding("UTF-8");

		// Récupération les paramètres de la JSP
		categorieId = Integer.parseInt(request.getParameter("categorieFiltre"));
		typeFiltre = request.getParameter("typeFiltre");

		mapCategorie = (Map<Integer, Categorie>) request.getServletContext().getAttribute(ATTR_MAP_CATEGORIES);
		categorieFiltre = mapCategorie.get(categorieId);

		if (ACHAT.name().equalsIgnoreCase(typeFiltre)) {
			encheresOuvertes = Boolean.parseBoolean(request.getParameter("chkEncheresOuvertes"));
			if (encheresOuvertes) {
				listFiltres.add(Filtre.ENCHERE_OUVERTES);
			}

			encheresEnCours = Boolean.parseBoolean(request.getParameter("chkMesEnchereEnCours"));
			if (encheresEnCours) {
				listFiltres.add(Filtre.MES_ENCHERES_EN_COURS);
			}

			ventesTerminees = Boolean.parseBoolean(request.getParameter("chkMesEncheresRemportes"));
			if (ventesTerminees) {
				listFiltres.add(Filtre.MES_ENCHERES_REMPORTES);
			}
		} else if (VENTE.name().equals(typeFiltre)) {
			ventesEnCours = Boolean.parseBoolean(request.getParameter("chkVentesEnCours"));
			if (ventesEnCours) {
				listFiltres.add(Filtre.VENTES_EN_COURS);
			}
			ventesNonDebutes = Boolean.parseBoolean(request.getParameter("chkVentesNonDebutes"));
			if (ventesNonDebutes) {
				listFiltres.add(Filtre.VENTES_NON_DEBUTES);
			}
			ventesTerminees = Boolean.parseBoolean(request.getParameter("chkVentesTerminees"));
			if (ventesTerminees) {
				listFiltres.add(Filtre.VENTES_NON_DEBUTES);
			}

		}
		
		utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");
		try {
			mapArticleAffiche = creerMapArticlesFiltres(utilisateur,categorieFiltre, listFiltres);
		} catch (FiltreInexistantException fie) {
			fie.printStackTrace();//TODO voir comment gérer cette exception
		}
		request.setAttribute(ATTR_MAP_ARTICLES_AFFICHES, mapArticleAffiche);
		getServletContext().getRequestDispatcher(JSP_ACCUEIL).forward(request, response);

	}

	/**
	 * 
	 * Méthode permettant de créer une map avec les articles filtrées selon les
	 * critères en paramètre.
	 * 
	 * @param categorieFiltre la categorie dans laquelle la catégorie doit se trouvée
	 * @param listFiltres listes des filtres à appliquer à la map
	 * @return la map avec les articles filtrées selon les
	 * critères en paramètre
	 * @throws FiltreInexistantException si le filtre n'est pas géré dans la méthode
	 */
	private Map<Integer, ArticleVendu> creerMapArticlesFiltres(Utilisateur utilisateur,Categorie categorieFiltre, List<Filtre> listFiltres) throws FiltreInexistantException {
		Map<Integer, ArticleVendu> mapArticlesFiltres = new HashMap<>();
		Map<Integer, ArticleVendu> articles = (Map<Integer, ArticleVendu>) getServletContext()
				.getAttribute(APP_ATTR_MAP_ARTICLES);
		
		for (ArticleVendu article : articles.values()) {
			if (categorieFiltre.equals(article.getCategorie())) {
				boolean ajouterArticle = true;

				for (Filtre filtre : listFiltres) {
					if (ajouterArticle) {
						switch (filtre) {
						case ENCHERE_OUVERTES:
							ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now());
							break;
						case MES_ENCHERES_EN_COURS:
							ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now())
									      && article.getDateDebutEncheres().isBefore(LocalDateTime.now());
							break;
						case MES_ENCHERES_REMPORTES:
							ajouterArticle = article.getDateFinEncheres().isBefore(LocalDateTime.now())
						                  && article.getAcheteur().equals(utilisateur);
							break;  
						case VENTES_EN_COURS:
							ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now())
						                  && article.getDateDebutEncheres().isBefore(LocalDateTime.now())
						                  && article.getVendeur().equals(utilisateur);
							break;
						case VENTES_NON_DEBUTES:
							ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now())
			                  && article.getDateDebutEncheres().isAfter(LocalDateTime.now())
			                  && article.getVendeur().equals(utilisateur);
							break;
						case VENTES_TERMINEES:
							ajouterArticle = article.getDateFinEncheres().isBefore(LocalDateTime.now())
			                  && article.getVendeur().equals(utilisateur);
							break;
						default:
							throw new FiltreInexistantException("Il n'y a aucune methode pour gérer le filtre : " + filtre.name());
						}
					}

				}
				if(ajouterArticle) {
					mapArticlesFiltres.put(article.getNoArticle(), article);
				}
			}

		}
		return mapArticlesFiltres;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		updateCategories();
		updateArticles();
	}

	private void updateCategories() {
		List<Categorie> categories = new ArrayList<>();
		Map<Integer, Categorie> mapCategories = new HashMap<>();
		CategorieManager manager = CategorieManager.getInstance();
		try {
			mapCategories = manager.getCategories();
			categories.addAll(mapCategories.values());
		} catch (BLLException blle) {
			blle.printStackTrace();// TODO voir si possible de faire mieux en gestion de
		}
		getServletContext().setAttribute("listeCategories", categories);//
		getServletContext().setAttribute(ATTR_MAP_CATEGORIES, mapCategories);
	}

	private void updateArticles() {
		Map<Integer, ArticleVendu> articles = new HashMap<>();
		ArticleManager manager = ArticleManager.getInstance();

		try {
			articles = manager.getMapArticles();
		} catch (BLLException blle) {
			blle.printStackTrace();// TODO voir si possible de faire mieux en gestion de
		}
		getServletContext().setAttribute(APP_ATTR_MAP_ARTICLES, articles);
	}
}
