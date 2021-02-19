package org.eni.encheres.ihm.servlets;

import static org.eni.encheres.ihm.servlets.TypeFiltre.ACHAT;
import static org.eni.encheres.ihm.servlets.TypeFiltre.VENTE;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.ihm.exceptions.FiltreInexistantException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/ServletAccueil")
public class ServletAccueil extends HttpServlet {

	private static final String APP_ENCODAGE = "UTF-8";
	private static final String APP_ATTR_UTILISATEUR = "utilisateur";
	private static final String APP_ATTR_MAP_ARTICLES = "mapArticles";
	private static final String APP_ATTR_MAP_CATEGORIES = "mapCategories";

	private static final String ATTR_MAP_ARTICLES_AFFICHES = "mapArticlesAffiches";

	private static final String PARAM_VENTES_TERMINEES = "chkVentesTerminees";
	private static final String PARAM_VENTES_NON_DEBUTES = "chkVentesNonDebutes";
	private static final String PARAM_VENTES_EN_COURS = "chkVentesEnCours";
	private static final String PARAM_MES_ENCHERES_REMPORTES = "chkMesEncheresRemportes";
	private static final String PARAM_MES_ENCHERE_EN_COURS = "chkMesEnchereEnCours";
	private static final String PARAM_ENCHERES_OUVERTES = "chkEncheresOuvertes";
	private static final String PARAM_TYPE_FILTRE = "typeFiltre";
	private static final String PARAM_CATEGORIE_FILTRE = "categorieFiltre";
	private static final String PARAM_RECHERCHE_TEXTE = "rechercheTexte";
	private static final String PARAM_RECHERCHE = "recherche";

	private static final String JSP_ACCUEIL = "/accueil";

	private static final long serialVersionUID = 1L;

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
		Map<Integer, ArticleVendu> mapArticleAffiche = new HashMap<>();

		// Encodage de la reponse http en utf-8
		request.setCharacterEncoding(APP_ENCODAGE);

		if (request.getParameter(PARAM_RECHERCHE) != null) {

			mapArticleAffiche = creerMapArticlesAffiches(request);

			// Chargement de l'attribut dans la requete
			request.setAttribute(ATTR_MAP_ARTICLES_AFFICHES, mapArticleAffiche);

			// Envoi de la requete au navigateur
			getServletContext().getRequestDispatcher(JSP_ACCUEIL).forward(request, response);

		} else {
			doGet(request, response);
		}

	}

	/**
	 * 
	 * Méthode permettant de mettre à jour la liste des catégories dans le contexte
	 * application en fonction du systeme de persistance
	 */
	private void updateCategories() {
		Map<Integer, Categorie> mapCategories = new ConcurrentHashMap<>();
		CategorieManager manager = CategorieManager.getInstance();
		try {
			mapCategories = manager.getCategories();
		} catch (BLLException blle) {
			blle.printStackTrace();
		}
		getServletContext().setAttribute(APP_ATTR_MAP_CATEGORIES, mapCategories);
	}

	/**
	 * 
	 * Méthode permettant de mettre à jour la liste des articles dans le contexte
	 * application en fonction du systeme de persistance
	 */
	private void updateArticles() {
		Map<Integer, ArticleVendu> articles = new ConcurrentHashMap<>();
		ArticleManager articleManager = ArticleManager.getInstance();
		EnchereManager enchereManager = EnchereManager.getInstance();
		
		//Récupération des articles dans le systeme de persistance
		try {
			articles = articleManager.getMapArticles();
		} catch (BLLException blle) {
			blle.printStackTrace();
		}
		
		//Mise à jour des acheteurs de la liste d'articles
		for (ArticleVendu articleCourant : articles.values()) {
			articleCourant = enchereManager.ajouterAcheteurToArticle(articleCourant);
		}
		
		getServletContext().setAttribute(APP_ATTR_MAP_ARTICLES, articles);
	}

	/**
	 * Méthode permettant de créer une map des articles remplissant les
	 * caractèristique de la recherche
	 * 
	 * @param request
	 *            http request
	 * @return une map des articles remplissant les caractèristique de la recherche
	 */
	private Map<Integer, ArticleVendu> creerMapArticlesAffiches(HttpServletRequest request) {
		Categorie categorieFiltre;
		List<Filtre> listFiltres = new ArrayList<>();
		Map<Integer, ArticleVendu> mapArticleAffiche = new HashMap<>();
		Utilisateur utilisateur;
		String texteRecherche;

		// Récupération les paramètres de la requete
		texteRecherche = request.getParameter(PARAM_RECHERCHE_TEXTE);
		categorieFiltre = getCategorieFromRequest(request);
		listFiltres = getListeFiltres(request);
		utilisateur = (Utilisateur) request.getSession().getAttribute(APP_ATTR_UTILISATEUR);

		// Mise à jour de la map article
		try {
			mapArticleAffiche = creerMapArticlesFiltres(utilisateur, categorieFiltre, listFiltres, texteRecherche);
		} catch (FiltreInexistantException fie) {
			fie.printStackTrace();
		}

		return mapArticleAffiche;
	}

	/**
	 * Méthode permettant de recuperer la categorie a partir de la requete
	 * 
	 * @param request
	 *            requete http
	 * @return la categorie selectionné par l'utilisateur
	 */
	private Categorie getCategorieFromRequest(HttpServletRequest request) {
		Map<Integer, Categorie> mapCategorie;
		Categorie categorieFiltre;
		int categorieId;
		categorieId = Integer.parseInt(request.getParameter(PARAM_CATEGORIE_FILTRE));
		mapCategorie = (Map<Integer, Categorie>) request.getServletContext().getAttribute(APP_ATTR_MAP_CATEGORIES);
		categorieFiltre = mapCategorie.get(categorieId);

		return categorieFiltre;
	}

	/**
	 * Méthode permettant de recuperer la liste des filtres pour l'affichage des
	 * articles
	 * 
	 * @param request
	 *            requette http
	 */
	private List<Filtre> getListeFiltres(HttpServletRequest request) {
		boolean encheresOuvertes, encheresEnCours, encheresRemportes;
		boolean ventesEnCours, ventesNonDebutes, ventesTerminees;
		String typeFiltre;
		List<Filtre> listFiltres = new ArrayList<>();
		typeFiltre = request.getParameter(PARAM_TYPE_FILTRE);

		if (ACHAT.name().equalsIgnoreCase(typeFiltre)) {
			encheresOuvertes = checkToBoolean(request.getParameter(PARAM_ENCHERES_OUVERTES));
			if (encheresOuvertes) {
				listFiltres.add(Filtre.ENCHERE_OUVERTES);
			}

			encheresEnCours = checkToBoolean(request.getParameter(PARAM_MES_ENCHERE_EN_COURS));
			if (encheresEnCours) {
				listFiltres.add(Filtre.MES_ENCHERES_EN_COURS);
			}

			encheresRemportes = checkToBoolean(request.getParameter(PARAM_MES_ENCHERES_REMPORTES));
			if (encheresRemportes) {
				listFiltres.add(Filtre.MES_ENCHERES_REMPORTES);
			}
		} else if (VENTE.name().equalsIgnoreCase(typeFiltre)) {
			ventesEnCours = checkToBoolean(request.getParameter(PARAM_VENTES_EN_COURS));
			if (ventesEnCours) {
				listFiltres.add(Filtre.VENTES_EN_COURS);
			}
			ventesNonDebutes = checkToBoolean(request.getParameter(PARAM_VENTES_NON_DEBUTES));
			if (ventesNonDebutes) {
				listFiltres.add(Filtre.VENTES_NON_DEBUTES);
			}
			ventesTerminees = checkToBoolean(request.getParameter(PARAM_VENTES_TERMINEES));
			if (ventesTerminees) {
				listFiltres.add(Filtre.VENTES_TERMINEES);
			}

		}
		return listFiltres;
	}

	/**
	 * 
	 * Méthode permettant de créer une map avec les articles filtrées selon les
	 * critères en paramètre.
	 * 
	 * @param categorieFiltre
	 *            la categorie dans laquelle la catégorie doit se trouvée
	 * @param listFiltres
	 *            listes des filtres à appliquer à la map
	 * @return la map avec les articles filtrées selon les critères en paramètre
	 * @throws FiltreInexistantException
	 *             si le filtre n'est pas géré dans la méthode
	 */
	private Map<Integer, ArticleVendu> creerMapArticlesFiltres(Utilisateur utilisateur, Categorie categorieFiltre,
			List<Filtre> listFiltres, String texteRecherche) throws FiltreInexistantException {
		Map<Integer, ArticleVendu> mapArticlesFiltres = new HashMap<>();
		Map<Integer, ArticleVendu> articles = (Map<Integer, ArticleVendu>) getServletContext()
				.getAttribute(APP_ATTR_MAP_ARTICLES);
		
		for (ArticleVendu article : articles.values()) {
			//test filtre categorie 
			if (categorieFiltre.getNoCategorie() == article.getCategorie().getNoCategorie()) {
				boolean ajouterArticle = false;
				//test filtre texte s'il existe
				if (texteRecherche != null
						&& !article.getNomArticle().toLowerCase().contains(texteRecherche.toLowerCase())) {
					ajouterArticle = false;
				} else {
					// test s'il y des filtres en plus
					if (listFiltres.size() == 0) {
						ajouterArticle = true;
					} else {
						for (Filtre filtre : listFiltres) {
							if (!ajouterArticle) {
								switch (filtre) {
								case ENCHERE_OUVERTES:
									ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now());
									break;
								case MES_ENCHERES_EN_COURS:
									ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now())
											&& article.getDateDebutEncheres().isBefore(LocalDateTime.now())
											&& utilisateur.getMapEncheres().containsKey(article.getNoArticle());

									break;
								case MES_ENCHERES_REMPORTES:
									if (article.getAcheteur() != null) {
										ajouterArticle = article.getDateFinEncheres().isBefore(LocalDateTime.now())
												&& article.getAcheteur().getNoUtilisateur()
														.equals(utilisateur.getNoUtilisateur());
									} else {
										ajouterArticle = false;
									}

									break;
								case VENTES_EN_COURS:
									ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now())
											&& article.getDateDebutEncheres().isBefore(LocalDateTime.now())
											&& article.getVendeur().getNoUtilisateur()
													.equals(utilisateur.getNoUtilisateur());
									break;
								case VENTES_NON_DEBUTES:
									ajouterArticle = article.getDateFinEncheres().isAfter(LocalDateTime.now())
											&& article.getDateDebutEncheres().isAfter(LocalDateTime.now());
									break;
								case VENTES_TERMINEES:
									ajouterArticle = article.getDateFinEncheres().isBefore(LocalDateTime.now());
									break;
								default:
									throw new FiltreInexistantException(
											"Il n'y a aucune methode pour gérer le filtre : " + filtre.name());
								}
							}
						}

					}

				}
				if (ajouterArticle) {
					mapArticlesFiltres.put(article.getNoArticle(), article);
				}
			}

		}

		return mapArticlesFiltres;
	}

	/**
	 * 
	 * Méthode permettant d'obtenir à partir du resultat d'une checkbox
	 * 
	 * @param checkParametre
	 *            parametre à convertir
	 * @return vrai si la checkbox est cochée, renvoi faux si le parametre est null.
	 */
	public boolean checkToBoolean(String checkParametre) {

		if (checkParametre == null || !checkParametre.equalsIgnoreCase("on")) {
			return false;
		}
		return true;
	}
}
