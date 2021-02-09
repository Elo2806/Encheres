package org.eni.encheres.bo;

import java.time.LocalDate;

/**
 * Classe modélisant une enchere
 * @author ElCaTar
 *
 */
public class Enchere {

	private LocalDate dateEnchere;
	private Integer montantEnchere;
	private Utilisateur utilsateur;
	private ArticleVendu article;
	
	
	// Constructeurs
	/**
	 * Constructeur
	 */
	public Enchere() {
	}
	
	/**
	 * Constructeur
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param utilsateur
	 * @param article
	 */
	public Enchere(LocalDate dateEnchere, Integer montantEnchere, Utilisateur utilsateur, ArticleVendu article) {
		this();
		setDateEnchere(dateEnchere);
		setMontantEnchere(montantEnchere);
		setUtilsateur(utilsateur);
		setArticle(article);
	}
	// Getters et Setters
	/**
	 * Méthode permettant de modifier dateEnchere
	 * @param dateEnchere le new dateEnchere
	 */
	private void setDateEnchere(LocalDate dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	/**
	 * Méthode permettant de modifier montantEnchere
	 * @param montantEnchere le new montantEnchere
	 */
	private void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	/**
	 * Méthode permettant de modifier utilsateur
	 * @param utilsateur le new utilsateur
	 */
	private void setUtilsateur(Utilisateur utilsateur) {
		this.utilsateur = utilsateur;
	}
	/**
	 * Méthode permettant de modifier article
	 * @param article le new article
	 */
	private void setArticle(ArticleVendu article) {
		this.article = article;
	}
	/**
	 * Méthode permettant de récupérer dateEnchere
	 * @return le dateEnchere
	 */
	public LocalDate getDateEnchere() {
		return dateEnchere;
	}
	/**
	 * Méthode permettant de récupérer montantEnchere
	 * @return le montantEnchere
	 */
	public Integer getMontantEnchere() {
		return montantEnchere;
	}
	/**
	 * Méthode permettant de récupérer utilsateur
	 * @return le utilsateur
	 */
	public Utilisateur getUtilsateur() {
		return utilsateur;
	}
	/**
	 * Méthode permettant de récupérer article
	 * @return le article
	 */
	public ArticleVendu getArticle() {
		return article;
	}
	
	
	
}
