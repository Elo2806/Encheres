
package org.eni.encheres.bo;


import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe modélisant un article
 * @author Elodie
 * Créé le: 18 févr. 2021
 * Modifié le: 18 févr. 2021
 */
public class ArticleVendu implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDateTime dateDebutEncheres;
	private LocalDateTime dateFinEncheres;
	private Integer prixInitial;
	private Integer prixVente;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Categorie categorie;
	private Enchere enchereMax;
	private Retrait retrait;
	
	/**
	 *Construteur
	 */
	public ArticleVendu() {
		setPrixInitial(0);
		setPrixVente(0);
	}
	
	
	/**
	 *Construteur
	 *
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,
			Utilisateur vendeur, Categorie categorie) {
		this();
		setNomArticle(nomArticle);
		setDescription(description);
		setDateDebutEncheres(dateDebutEncheres);
		setDateFinEncheres(dateFinEncheres);
		setVendeur(vendeur);
		setCategorie(categorie);
		enchereMax = new Enchere(dateDebutEncheres, 0, vendeur, this);//enchere 
	}
	
	/**
	 * Constructeur l'objet sans le noArticle
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 * @param retrait
	 * @param noArticle
	 * @param Integer
	 */
	public ArticleVendu (String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie,Retrait retrait, Integer prixInitial ) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur,
				categorie);
		setRetrait(retrait);
		setPrixInitial(prixInitial);
		
		//Création de la liaison bidirectionnelle
		retrait.setArticle(this);
	}
	
	/**
	 * Constructeur complet de l'objet
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 * @param retrait
	 * @param noArticle
	 * @param Integer
	 */
	public ArticleVendu (String nomArticle, String description, LocalDateTime dateDebutEncheres,
			LocalDateTime dateFinEncheres, Utilisateur vendeur, Categorie categorie,Retrait retrait, int noArticle, Integer prixInitial ) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur,
				categorie);
		setRetrait(retrait);
		setNoArticle(noArticle);
		setPrixInitial(prixInitial);
		
		//Création de la liaison bidirectionnelle
		retrait.setArticle(this);
	}
	

	/**
	 * Constructeur pour créer un article léger 
	 * 
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 * @param noArticle
	 */
	public ArticleVendu(String nomArticle, String description, LocalDateTime dateDebutEncheres, LocalDateTime dateFinEncheres,Utilisateur vendeur,
			Categorie categorie, int noArticle) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres, vendeur, categorie);
		setNoArticle(noArticle);
	}

	// Getters et Setters
	/**
	 * Méthode permettant de récupérer noArticle
	 * @return le noArticle
	 */
	public Integer getNoArticle() {
		return noArticle;
	}
	/**
	 * Méthode permettant de modifier noArticle
	 * @param noArticle le new noArticle
	 */
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	/**
	 * Méthode permettant de récupérer nomArticle
	 * @return le nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	/**
	 * Méthode permettant de modifier nomArticle
	 * @param nomArticle le new nomArticle
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	/**
	 * Méthode permettant de récupérer description
	 * @return le description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Méthode permettant de modifier description
	 * @param description le new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Méthode permettant de récupérer dateDebutEncheres
	 * @return le dateDebutEncheres
	 */
	public LocalDateTime getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	/**
	 * Méthode permettant de modifier dateDebutEncheres
	 * @param dateDebutEncheres le new dateDebutEncheres
	 */
	public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	/**
	 * Méthode permettant de récupérer dateFinEncheres
	 * @return le dateFinEncheres
	 */
	public LocalDateTime getDateFinEncheres() {
		return dateFinEncheres;
	}
	/**
	 * Méthode permettant de modifier dateFinEncheres
	 * @param dateFinEncheres le new dateFinEncheres
	 */
	public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	/**
	 * Méthode permettant de récupérer prixInitial
	 * @return le prixInitial
	 */
	public Integer getPrixInitial() {
		return prixInitial;
	}
	/**
	 * Méthode permettant de modifier prixInitial
	 * @param prixInitial le new prixInitial
	 */
	public void setPrixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}
	/**
	 * Méthode permettant de récupérer prixVnete
	 * @return le prixVnete
	 */
	public Integer getPrixVente() {
		return prixVente;
	}
	/**
	 * Méthode permettant de modifier prixVnete
	 * @param prixVnete le new prixVnete
	 */
	public void setPrixVente(Integer prixVente) {
		this.prixVente = prixVente;
	}
	/**
	 * Méthode permettant de récupérer vendeur
	 * @return le vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}
	/**
	 * Méthode permettant de modifier vendeur
	 * @param vendeur le new vendeur
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	/**
	 * Méthode permettant de récupérer acheteur
	 * @return le acheteur
	 */
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	/**
	 * Méthode permettant de modifier acheteur
	 * @param acheteur le new acheteur
	 */
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	/**
	 * Méthode permettant de récupérer categorie
	 * @return le categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * Méthode permettant de modifier categorie
	 * @param categorie le new categorie
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	/**
	 * Méthode permettant de récupérer enchereMax
	 * @return le enchereMax
	 */
	public Enchere getEnchereMax() {
		return enchereMax;
	}


	/**
	 * Méthode permettant de modifier enchereMax
	 * @param enchereMax new enchereMax
	 */
	public void setEnchereMax(Enchere penchereMax) {
		enchereMax = penchereMax;
	}


	/**
	 * Méthode permettant de récupérer retrait
	 * @return le retrait
	 */
	public Retrait getRetrait() {
		return retrait;
	}


	/**
	 * Méthode permettant de modifier retrait
	 * @param retrait new retrait
	 */
	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}

}
