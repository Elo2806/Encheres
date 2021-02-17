/**
 * 
 */
package org.eni.encheres.dal;

import java.time.LocalDateTime;

import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Retrait;
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
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie,Retrait retrait) {

		ArticleVendu newArticle = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur,
				categorie);
		newArticle.setRetrait(retrait);
		return newArticle;
	}
	/**
	 * 
	 * Méthode permettant de créer une instance Enchere à partir des paramètres
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param utilisateur
	 * @param article
	 * @return
	 */
	public static Enchere creerEnchere(LocalDateTime dateEnchere, Integer montantEnchere, Utilisateur utilisateur, ArticleVendu article) {
		
		Enchere newEnchere = new Enchere(dateEnchere, montantEnchere, utilisateur, article);
		return newEnchere;
		
	}
	
	/**
	 * 
	 * Méthode permettant de créer une instance Retrait à partir des paramètres
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param article
	 * @return
	 */
	public static Retrait creerRetrait(String rue, String codePostal, String ville, ArticleVendu article) {
		Retrait newRetrait = new Retrait(rue, codePostal, ville, article);
		return newRetrait;
	}
	
	public static Categorie creerCategorie(String libelle,Integer noCategorie) {
		Categorie newCategorie = new Categorie(libelle);
		newCategorie.setNoCategorie(noCategorie);
		
		return newCategorie;
		
	}
}
