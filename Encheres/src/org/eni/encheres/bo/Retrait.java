/**
 * 
 */
package org.eni.encheres.bo;

import java.io.Serializable;

/**
 * 
 * @author Taharqa
 * Créé le: 9 févr. 2021
 * Mofifié le: 9 févr. 2021
 */
public class Retrait implements Serializable {

	private String rue;
	private String codePostal;
	private String ville;
	private ArticleVendu article;
	
	//Constructeurs
	/**
	 * 
	 * Constructeur
	 */
	public Retrait() {
		
	}
	
	/**
	 * 
	 * Constructeur
	 * @param rue
	 * @param codePostal
	 * @param ville
	 * @param article
	 */
	public Retrait(String rue, String codePostal, String ville, ArticleVendu article) {
		this();
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setArticle(article);
	}
	//Setters et getters

	/**
	 * Méthode permettant de récupérer rue
	 * @return le rue
	 */
	public String getRue() {
		return rue;
	}

	/**
	 * Méthode permettant de modifier rue
	 * @param rue new rue
	 */
	public void setRue(String rue) {
		this.rue = rue;
	}

	/**
	 * Méthode permettant de récupérer codePostal
	 * @return le codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}

	/**
	 * Méthode permettant de modifier codePostal
	 * @param codePostal new codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	/**
	 * Méthode permettant de récupérer ville
	 * @return le ville
	 */
	public String getVille() {
		return ville;
	}

	/**
	 * Méthode permettant de modifier ville
	 * @param ville new ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/**
	 * Méthode permettant de récupérer article
	 * @return le article
	 */
	public ArticleVendu getArticle() {
		return article;
	}

	/**
	 * Méthode permettant de modifier article
	 * @param article new article
	 */
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	
	
	
}
