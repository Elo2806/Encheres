/**
 * 
 */
package org.eni.encheres.bo;

/**
 * 
 * @author Taharqa
 * Créé le: 9 févr. 2021
 * Mofifié le: 9 févr. 2021
 */
public class Retrait {

	private String rue;
	private String codePostal;
	private String ville;
	private ArticleVendu article;
	
	//Constructeurs
	
	public Retrait() {
		
	}
	
	public Retrait(String rue, String codePostal, String ville, ArticleVendu article) {
		this();
		setRue(rue);
		setCodePostal(codePostal);
		setVille(ville);
		setArticle(article);
	}
	//Setters et getters
	
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public ArticleVendu getArticle() {
		return article;
	}
	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	
	
	
}
