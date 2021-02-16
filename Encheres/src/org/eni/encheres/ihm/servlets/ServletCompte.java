package org.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.ihm.exceptions.MotDePasseException;

/**
 * Servlet implementation class ServletCreationCompte
 */
@WebServlet("/ServletCompte")
public class ServletCompte extends HttpServlet {

	private static final String SESSION_ATTR_UTILISATEUR = "utilisateur";
	private static final String JSP_PROFIL = "/profil";
	private static final String ATTR_ERREUR_INSERTION = "erreurInsertion";
	private static final String ATTR_ERREUR_IDENTIFIANT = "erreurIdentifiant";
	private static final String ATTR_ERREUR_MDP = "erreurMdp";

	private static final String ERREUR_GENERIQUE = "generique";
	private static final String ERREUR_IDENTIFIANT = "identifiant";
	private static final String ERREUR_MDP = "mdp";

	private static final String ATTR_ERREUR_MESSAGE = "ErreurMessage";

	private static final String SERVLET_CONNEXION = "/ServletConnexion";

	private static final String JSP_COMPTE = "/compte";

	private static final String PARAM_OLD_MDP = "oldMdp";
	private static final String PARAM_NEW_MDP = "newMdp";
	private static final String PARAM_VILLE = "ville";
	private static final String PARAM_CODE_POSTAL = "codePostal";
	private static final String PARAM_RUE = "rue";
	private static final String PARAM_TELEPHONE = "telephone";
	private static final String PARAM_EMAIL = "email";
	private static final String PARAM_PRENOM = "prenom";
	private static final String PARAM_NOM = "nom";
	private static final String PARAM_PSEUDO = "pseudo";
	private static final String PARAM_CONFIRMATION = "confirmation";
	private static final String PARAM_MDP = "mdp";
	private static final String PARAM_MODIFICATION = "modification";
	private static final String PARAM_MODIF_MDP = "modifMdp";
	private static final String PARAM_SUPPRIMER = "supprimer";
	private static final String PARAM_MODIFICATION_COMPTE = "modificationCompte";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Boolean modification;
		modification = Boolean.parseBoolean(request.getParameter(PARAM_MODIFICATION));

		Utilisateur utilisateurAffiche = (Utilisateur) request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR);
		request.setAttribute("utilisateurAffiche", utilisateurAffiche);

		if (modification != null && modification) {

			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);

		} else {
			getServletContext().getRequestDispatcher(JSP_PROFIL).forward(request, response);
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean ErreurSaisie = false;
		request.setCharacterEncoding("UTF-8");
		Utilisateur user = (Utilisateur) request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR);

		String pseudo = request.getParameter(PARAM_PSEUDO);
		String nom = request.getParameter(PARAM_NOM);
		String prenom = request.getParameter(PARAM_PRENOM);
		String email = request.getParameter(PARAM_EMAIL);
		String telephone = request.getParameter(PARAM_TELEPHONE);
		String rue = request.getParameter(PARAM_RUE);
		String codePostal = request.getParameter(PARAM_CODE_POSTAL);
		String ville = request.getParameter(PARAM_VILLE);
		String mdp = request.getParameter(PARAM_MDP);
		String oldMdp = request.getParameter(PARAM_OLD_MDP);
		String newMdp = request.getParameter(PARAM_NEW_MDP);
		String confirmation = request.getParameter(PARAM_CONFIRMATION);
		UtilisateurManager manager = UtilisateurManager.getInstance();
		System.out.println(request.getParameter(PARAM_SUPPRIMER));
		System.out.println(request.getParameter("enregistrer"));
		if (!(Boolean.parseBoolean(request.getParameter(PARAM_MODIFICATION_COMPTE)))) {
			creerCompte(request, response, ErreurSaisie, pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
					mdp, confirmation, manager);
			
		} else {
			if (request.getParameter(PARAM_SUPPRIMER)!=null) {
				supprimerCompte(request, response, user);
			} else {
			modifierCompte(request, response, ErreurSaisie, user, pseudo, email, telephone, rue, codePostal, ville,
					oldMdp, newMdp, confirmation, manager);
			}
		}
	}

	/**
	 * Méthode permettant de mettre à jour un compte utilisateur après les contrôles
	 * metiers IHM (saisie du bon mdp + contrôle des 2 mdp à changer identiques +
	 * pseudo et email non existants en base si modif)
	 * 
	 * @param request
	 * @param response
	 * @param ErreurSaisie
	 * @param user
	 * @param pseudo
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param oldMdp
	 * @param newMdp
	 * @param confirmation
	 * @param manager
	 * @throws DALException
	 * @throws ServletException
	 * @throws IOException
	 */
	private void modifierCompte(HttpServletRequest request, HttpServletResponse response, boolean ErreurSaisie,
			Utilisateur user, String pseudo, String email, String telephone, String rue, String codePostal,
			String ville, String oldMdp, String newMdp, String confirmation, UtilisateurManager manager)
			throws ServletException, IOException {
		/*
		 * TODO
		 * Creer methode differenciée modifierUtilisateur (admin + user)
		 */

		String nom;
		String prenom;
		Integer noUtilisateur = user.getNoUtilisateur();
		try {
			controlerMdp(oldMdp, user.getMotDePasse());
			request.setAttribute(ATTR_ERREUR_MDP, false);
		} catch (MotDePasseException mdpe) {
			request.setAttribute(ATTR_ERREUR_MDP, true);
			ErreurSaisie = true;
		}
		Utilisateur utilisateurAffiche = null;
		if (!ErreurSaisie) {
			if (Boolean.parseBoolean(request.getParameter(PARAM_MODIF_MDP))) {
				try {
					controlerMdp(newMdp, confirmation);
					user.setMotDePasse(newMdp);
					request.setAttribute(ATTR_ERREUR_MDP, false);
					
					utilisateurAffiche = manager.modifierUtilisateur(user.getPseudo(), user.getNom(), user.getPrenom(),
							user.getEmail(), user.getTelephone(), user.getRue(), user.getCodePostal(), user.getVille(),
							user.getMotDePasse(), user.getNoUtilisateur());
					request.setAttribute(ATTR_ERREUR_INSERTION, false);
				} catch (MotDePasseException mdpe) {
					request.setAttribute(ATTR_ERREUR_MDP, true);
					ErreurSaisie = true;
				} catch (BLLException blle) {
					request.setAttribute(ATTR_ERREUR_INSERTION, true);
					blle.printStackTrace();
					ErreurSaisie = true;
				}
			} else {
				// nom et prénoms non modifiables par user donc impossible de les récupérer en
				// paramètres
				nom = user.getNom();
				prenom = user.getPrenom();
				if (!((pseudo.equals(user.getPseudo())) && (email.equals(user.getEmail())))) {
					if (!pseudo.equals(user.getPseudo())) {
						try {
							manager.controlePseudoExistant(pseudo);
							request.setAttribute(ATTR_ERREUR_IDENTIFIANT, false);
						} catch (BLLException e) {
							request.setAttribute(ATTR_ERREUR_IDENTIFIANT, true);
							ErreurSaisie = true;
						}
					}
					if (!email.equals(user.getEmail())) {
						try {
							manager.controleEmailExistant(email);
							request.setAttribute(ATTR_ERREUR_IDENTIFIANT, false);
						} catch (BLLException e) {
							request.setAttribute(ATTR_ERREUR_IDENTIFIANT, true);
							ErreurSaisie = true;
						}
					}
				}
				try {
					utilisateurAffiche = manager.modifierUtilisateur(pseudo, nom, prenom, email, telephone, rue,
							codePostal, ville, oldMdp, noUtilisateur);
					request.setAttribute(ATTR_ERREUR_INSERTION, false);
				} catch (BLLException blle) {
					request.setAttribute(ATTR_ERREUR_INSERTION, true);
					blle.printStackTrace();
					ErreurSaisie = true;
				}
			}
		}
		if (ErreurSaisie) {
			request.setAttribute("utilisateurAffiche", request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR));
			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);
		} else {
			// Mise à jour de la session utilisateur :
			request.getSession().setAttribute(SESSION_ATTR_UTILISATEUR, utilisateurAffiche);
			// Transferer l'affichage (pour JSP) :
			request.setAttribute("utilisateurAffiche", utilisateurAffiche);
			getServletContext().getRequestDispatcher(JSP_PROFIL).forward(request, response);
		}
	}

	/**
	 * Méthode permettant de créer un nouveau compte après les contrôles metiers IHM
	 * (saisie mdp identique 2 fois + pseudo et email non existants en base)
	 * 
	 * @param request
	 * @param response
	 * @param ErreurSaisie
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param mdp
	 * @param confirmation
	 * @param manager
	 * @throws ServletException
	 * @throws IOException
	 */
	private void creerCompte(HttpServletRequest request, HttpServletResponse response, boolean ErreurSaisie,
			String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal,
			String ville, String mdp, String confirmation, UtilisateurManager manager)
			throws ServletException, IOException {
		try {
			controlerMdp(mdp, confirmation);
			request.setAttribute(ATTR_ERREUR_MDP, false);
		} catch (MotDePasseException mdpe) {
			request.setAttribute(ATTR_ERREUR_MDP, true);
			ErreurSaisie = true;
		}

		try {
			manager.controleIdentifiantNewUtilisateur(pseudo, email);
			request.setAttribute(ATTR_ERREUR_IDENTIFIANT, false);
		} catch (BLLException e) {
			request.setAttribute(ATTR_ERREUR_IDENTIFIANT, true);
			ErreurSaisie = true;
		}

		try {
			manager.ajouterUtilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
			request.setAttribute(ATTR_ERREUR_INSERTION, false);
		} catch (BLLException blle) {
			request.setAttribute(ATTR_ERREUR_INSERTION, true);
			blle.printStackTrace();
			ErreurSaisie = true;
		}

		if (ErreurSaisie) {
			Utilisateur utilisateurAffiche = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal,
					ville, "", 0, false);
			request.setAttribute("utilisateurAffiche", utilisateurAffiche);
			getServletContext().getRequestDispatcher(JSP_COMPTE + "?creation=true").forward(request, response);
		} else {
			getServletContext()
					.getRequestDispatcher(SERVLET_CONNEXION + "?identifiant=" + pseudo + "&motdepasse=" + mdp)
					.forward(request, response);
		}
	}

	/**
	 * 
	 * Méthode permettant de supprimer un compte (attribut 'supprime'=true dans le système de persistance)
	 * @param request
	 * @param response
	 * @param utilisateurAffiche
	 * @param suppression
	 * @throws ServletException
	 * @throws IOException
	 */
	private void supprimerCompte(HttpServletRequest request, HttpServletResponse response, Utilisateur utilisateurAffiche) throws ServletException, IOException {
		boolean ErreurSaisie = false;
		UtilisateurManager manager = UtilisateurManager.getInstance();
		try {
			controlerMdp(request.getParameter(PARAM_OLD_MDP), utilisateurAffiche.getMotDePasse());
			request.setAttribute(ATTR_ERREUR_MDP, false);
			utilisateurAffiche.setSupprime(true);
			
			manager.supprimerCompte(utilisateurAffiche);
			request.setAttribute(ATTR_ERREUR_INSERTION, false);
		} catch (MotDePasseException mdpe) {
			request.setAttribute(ATTR_ERREUR_MDP, true);
			ErreurSaisie = true;
		} catch (BLLException blle) {
			request.setAttribute(ATTR_ERREUR_INSERTION, true);
			blle.printStackTrace();
			ErreurSaisie = true;
		}
		
		if (ErreurSaisie) {
			request.setAttribute("utilisateurAffiche", request.getSession().getAttribute(SESSION_ATTR_UTILISATEUR));
			getServletContext().getRequestDispatcher(JSP_COMPTE).forward(request, response);
		} else {
			// Mise à jour de la session utilisateur :
			request.getSession().setAttribute(SESSION_ATTR_UTILISATEUR, utilisateurAffiche);
			getServletContext().getRequestDispatcher(SERVLET_CONNEXION).forward(request, response);;
		}
	}	

	private void controlerMdp(String mdp, String confirmation) throws MotDePasseException {
		if (!mdp.equals(confirmation)) {
			throw new MotDePasseException("La confirmation du mot de passe est différente du mot de passe");
		}
	}
	
}
