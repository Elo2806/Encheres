package org.eni.encheres.bo;

import java.io.Serializable;

/**
 * Classe modélisant une catégorie d'article
 * @author ElCaTar
 *
 */
public class Categorie implements Serializable {

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
	public void setNoCategorie(Integer noCategorie) {
		this.noCategorie = noCategorie;
	}
	/**
	 * Méthode permettant de modifier libelle
	 * @param libelle le new libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
}
