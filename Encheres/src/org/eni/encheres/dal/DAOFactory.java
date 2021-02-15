/**
 * 
 */
package org.eni.encheres.dal;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.articles.ArticleDAO;
import org.eni.encheres.dal.articles.ArticleDAOImpl;
import org.eni.encheres.dal.categories.CategorieDAOimpl;
import org.eni.encheres.dal.categories.CategorieDao;
import org.eni.encheres.dal.encheres.EnchereDAO;
import org.eni.encheres.dal.encheres.EnchereDAOimpl;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAO;
import org.eni.encheres.dal.utilisateurs.UtilisateurDAOimpl;

/**
 * @author Elodie
 * Créé le: 11 févr. 2021
 * Modifié le: 11 févr. 2021
 */
public abstract class DAOFactory {

	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'ArticleDAO
	 * @return ArticleDAO
	 */
	public static ArticleDAO getArticleDAO() {
		return new ArticleDAOImpl();
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'UtilisateurDAO
	 * @return UtilisateurDAO
	 */
	public static UtilisateurDAO getUtilisateurDAO() {
		return new UtilisateurDAOimpl();
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'EnchereDAO
	 * @return EnchereDAO
	 */
	public static EnchereDAO getEnchereDAO() {
		return new EnchereDAOimpl();
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance concrète d'EnchereDAO
	 * @return EnchereDAO
	 */
	public static CategorieDao getCatogorieDAO() {
		return new CategorieDAOimpl();
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance Utilisateur à partir des paramètres
	 * @param pseudo
	 * @param nom
	 * @param prenom
	 * @param email
	 * @param telephone
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param motDePasse
	 * @param credit
	 * @param administrateur
	 * @return
	 */
	public static Utilisateur creerUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String motDePasse, Integer credit, boolean administrateur, int noUtilisateur, boolean actif ) {

		Utilisateur newUtilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse, credit, administrateur);
		System.out.println(" nUtilisateur : "+ noUtilisateur);// TODO	à suppr après les tests
		newUtilisateur.setNoUtilisateur(noUtilisateur);
		newUtilisateur.setActif(actif);
		return newUtilisateur;
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance Article à partir des paramètres
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 * @return
	 */
	public static ArticleVendu creerArticle(String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie) {

		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur,
				categorie);

		return newArticle;
	}
}
