package org.eni.encheres.bo;


/**
 * Classe modélisant une catégorie d'article
 * @author ElCaTar
 *
 */
public class Categorie {

	private Integer noCategorie;
	private String libelle;
	
	
	// Constructeur
	/**
	 * Constructeur
	 */
	public Categorie() {
	}
	/**
	 * Constructeur
	 * @param noCategorie
	 * @param libelle
	 */
	public Categorie(String libelle) {
		this();
		setLibelle(libelle);
	}
	
	// Getters et Setters
	/**
	 * Méthode permettant de récupérer noCategorie
	 * @return le noCategorie
	 */
	public Integer getNoCategorie() {
		return noCategorie;
	}
	/**
	 * Méthode permettant de récupérer libelle
	 * @return le libelle
	 */
	public String getLibelle() {
		return libelle;
	}
	/**
	 * Méthode permettant de modifier noCategorie
	 * @param noCategorie le new noCategorie
	 */
	private void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * Méthode permettant de modifier libelle
	 * @param libelle le new libelle
	 */
	private void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
