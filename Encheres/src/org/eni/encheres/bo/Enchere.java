package org.eni.encheres.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Classe modélisant une enchere
 * @author ElCaTar
 *
 */
public class Enchere implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private LocalDateTime dateEnchere;
	private Integer montantEnchere;
	private Utilisateur utilisateur;
	private ArticleVendu article;
	
	
	/**
	 * Constructeur
	 */
	public Enchere() {
	}
	
	/**
	 * Constructeur
	 * 
	 * @param dateEnchere
	 * @param montantEnchere
	 * @param utilisateur
	 * @param article
	 */
	public Enchere(LocalDateTime dateEnchere, Integer montantEnchere, Utilisateur utilisateur, ArticleVendu article) {
		this();
		setDateEnchere(dateEnchere);
		setMontantEnchere(montantEnchere);
		setUtilsateur(utilisateur);
		setArticle(article);
	}
	
	// Getters et Setters
	/**
	 * Méthode permettant de modifier dateEnchere
	 * @param dateEnchere le new dateEnchere
	 */
	private void setDateEnchere(LocalDateTime dateEnchere) {
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
	 * Méthode permettant de modifier utilisateur
	 * @param utilisateur le new utilisateur
	 */
	private void setUtilsateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
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
	public LocalDateTime getDateEnchere() {
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
	 * Méthode permettant de récupérer utilisateur
	 * @return le utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	/**
	 * Méthode permettant de récupérer article
	 * @return le article
	 */
	public ArticleVendu getArticle() {
		return article;
	}
	
	
	
}
